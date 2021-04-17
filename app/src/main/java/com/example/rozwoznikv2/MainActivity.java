package com.example.rozwoznikv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText emailEt = (EditText) findViewById(R.id.et_email_login);


        Button goToRegistration = (Button) findViewById(R.id.btn_goToRegistration_login);
        goToRegistration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),registration_activity.class);
                startActivity(intent);

               final String email = emailEt.getText().toString();

                if(email.length()==0)
                {
                    emailEt.requestFocus();
                    emailEt.setError("Pole nie może być puste");
                }

            }
        });
    }
}