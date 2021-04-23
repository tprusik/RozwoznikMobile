package com.example.rozwoznikv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rozwoznikv2.DAO.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reff;
    PasswordEncrypter encrypter = new PasswordEncrypter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText emailEt = (EditText) findViewById(R.id.et_email_login);
        EditText passEt = (EditText) findViewById(R.id.edit_text_login_password);
        User user = new User();
        // reff = FirebaseDatabase.getInstance().getReference().child("User");

        Button goToLogin = (Button) findViewById(R.id.btn_goToLogin_login);


        goToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                reff = FirebaseDatabase.getInstance().getReference().child("User").child(emailEt.getText().toString().replace(".","_"));


                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.exists()) {
                            String email = snapshot.child("email").getValue().toString();
                            String secret = snapshot.child("password").getValue().toString();

                            if(checkEqualPassword(passEt.getText().toString(),secret))
                            Toast.makeText(MainActivity.this, "email: " + email + " pomyślnie odebrano dane", Toast.LENGTH_LONG).show();

                            else
                                Toast.makeText(MainActivity.this, "email lub hasło nieprawidłowe", Toast.LENGTH_LONG).show();

                       }
                        else
                            Toast.makeText(MainActivity.this,"Email nie znajduje się w bazie", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            });

        Button goToRegistration = (Button) findViewById(R.id.btn_goToRegistration_login);
        goToRegistration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),registration_activity.class);
                startActivity(intent); }


        });
    }

    private boolean checkEqualPassword(String pass, String secret){

       String receivedSecret =  encrypter.Encrypt(pass);

       if(receivedSecret.equals(secret))
           return true;
       else return false;
    }
}