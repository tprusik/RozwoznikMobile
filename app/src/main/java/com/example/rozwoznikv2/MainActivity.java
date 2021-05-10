package com.example.rozwoznikv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.rozwoznikv2.DAO.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reff;
    PasswordEncrypter encrypter = new PasswordEncrypter();

boolean IsLogin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("com.example.notatnikfinal", Context.MODE_PRIVATE);

        EditText emailEt = (EditText) findViewById(R.id.et_email_login);
        EditText passEt = (EditText) findViewById(R.id.edit_text_login_password);

        Button goToLogin = (Button) findViewById(R.id.btn_goToLogin_login);


        goToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                reff = FirebaseDatabase.getInstance().getReference().child("User").child(emailEt.getText().toString().replace(".","_"));

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.exists()) {

                            User newUser = snapshot.getValue(User.class);

                            if(checkEqualPassword(passEt.getText().toString(),newUser.getPassword())) {
                                Toast.makeText(MainActivity.this, "email: " + newUser.getEmail() + " pomyślnie odebrano dane " + newUser.getId(), Toast.LENGTH_LONG).show();
                                IsLogin=true;

                                prefs.edit().putBoolean("Islogin", IsLogin).commit();
                                prefs.edit().putString("userID",newUser.getId()).commit();
                                prefs.edit().putString("email",newUser.getEmail()).commit();

                            }
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


        Button goToAddAnnouncement = (Button) findViewById(R.id.btn_goToAddAnnouncement_login);
        goToAddAnnouncement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),AddAnnouncementActivity.class);
                startActivity(intent); }

        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_main_toolbar,menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.shareToolbar)
            Toast.makeText(MainActivity.this,"Przycisk Toolbar", Toast.LENGTH_LONG).show();

        if(id==R.id.addAnnouncementToolbar){
            Intent intent = new Intent(getApplicationContext(),AddAnnouncementActivity.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this,"Sukces", Toast.LENGTH_LONG).show();
    }

        if(id==R.id.accountToolbar){
            Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
            startActivity(intent);
        }

        return true;
    }

    private boolean checkEqualPassword(String pass, String secret){

       String receivedSecret =  encrypter.Encrypt(pass);

       if(receivedSecret.equals(secret))
           return true;
       else return false;
    }
}