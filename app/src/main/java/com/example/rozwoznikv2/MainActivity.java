package com.example.rozwoznikv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rozwoznikv2.DAO.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText emailEt = (EditText) findViewById(R.id.et_email_login);
        EditText passEt = (EditText) findViewById(R.id.edit_text_login_password);
        User user = new User();
        reff = FirebaseDatabase.getInstance().getReference().child("User");
        Button goToRegistration = (Button) findViewById(R.id.btn_goToRegistration_login);
        goToRegistration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // Intent intent = new Intent(getApplicationContext(),registration_activity.class);
               // startActivity(intent);

               final String email = emailEt.getText().toString();
               final String pass = passEt.getText().toString();

               user.setEmail(email);
               user.setPassword(pass);
               reff.push().setValue(user);
                Toast.makeText(MainActivity.this,"SUKCES dodany do bazy pora spać",Toast.LENGTH_LONG).show();
            }
        });
    }
}