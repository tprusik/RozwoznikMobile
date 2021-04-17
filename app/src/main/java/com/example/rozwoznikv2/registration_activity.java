package com.example.rozwoznikv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class registration_activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_activity);


        EditText nameEt = (EditText) findViewById(R.id.edit_name_registration);
        EditText phoneEt = (EditText) findViewById(R.id.edit_phoneNumber_registration);
        EditText emailEt = (EditText) findViewById(R.id.edit_email_registration);
        EditText passwordEt = (EditText) findViewById(R.id.edit_password_registration);
        EditText confirmPassdEt = (EditText) findViewById(R.id.edit_confirmPass_registration);

        List<EditText> editTextLIist = new ArrayList<EditText>();
        editTextLIist.add(nameEt);
        editTextLIist.add(phoneEt);
        editTextLIist.add(emailEt);
        editTextLIist.add(passwordEt);
        editTextLIist.add(confirmPassdEt);
        

        Button  confirmRegistrationBtn = (Button) findViewById(R.id.btn_confirmRegistration_registration);
       confirmRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                for (EditText el:editTextLIist
                     ) {
                    final String elString = el.getText().toString();
                    if(elString.length()==0)
                    {
                        el.requestFocus();
                        el.setError("Pole nie może być puste");
                    }

                }





            }

    });
}


}