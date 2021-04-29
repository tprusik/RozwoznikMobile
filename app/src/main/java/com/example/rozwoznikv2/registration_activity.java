package com.example.rozwoznikv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rozwoznikv2.DAO.User;
import com.example.rozwoznikv2.DAO.UserData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class registration_activity extends AppCompatActivity {

    EditText nameEt,phoneEt,emailEt,passEt,confirmPassEt;
    DatabaseReference reffUser ,reffData;
    PasswordEncrypter encrypter = new PasswordEncrypter();
    boolean isValid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_activity);

         nameEt = (EditText) findViewById(R.id.edit_name_registration);
         phoneEt = (EditText) findViewById(R.id.edit_phoneNumber_registration);
         emailEt = (EditText) findViewById(R.id.edit_email_registration);
         passEt = (EditText) findViewById(R.id.edit_password_registration);
         confirmPassEt = (EditText) findViewById(R.id.edit_confirmPass_registration);


        reffUser = FirebaseDatabase.getInstance().getReference().child("User");
        reffData = FirebaseDatabase.getInstance().getReference().child("UserData");

        List<EditText> editTextLIist = new ArrayList<EditText>();
        editTextLIist.add(nameEt);
        editTextLIist.add(phoneEt);
        editTextLIist.add(emailEt);
        editTextLIist.add(passEt);
        editTextLIist.add(confirmPassEt);
        

        Button  confirmRegistrationBtn = (Button) findViewById(R.id.btn_confirmRegistration_registration);
       confirmRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                isValid=true;

                ValidateEmail(emailEt.getText().toString());
                ValidatePassword(passEt.getText().toString());
                ValidateNullData(editTextLIist);

                if(isValid){
                    User user = new User();
                    UserData userData =  new UserData();

                    user.setEmail(emailEt.getText().toString());
                    user.setPassword(encrypter.Encrypt(passEt.getText().toString()));

                    userData.setName(nameEt.getText().toString());
                    userData.setPhone(phoneEt.getText().toString());

                    //reffUser.push().setValue(user);
                   // reffData.push().setValue(userData);

                    String email = user.getEmail().replace(".","_");
                    reffUser.child(email).setValue(user);
                    Toast.makeText(registration_activity.this,"SUKCES ",Toast.LENGTH_LONG).show();

                }


            }

    });
}


private void ValidateNullData(List<EditText> list){

    for (EditText el:list
    ) {
        final String elString = el.getText().toString();
        if(elString.length()==0)
        {
            el.requestFocus();
            el.setError("Pole nie może być puste");
            isValid=false;
        }


    }

}

private void ValidatePassword(String password) {

    Pattern digitCasePatten = Pattern.compile("[0-9 ]");

        if(password.length()<9){
            passEt.requestFocus();
            passEt.setError("Długość hasła powinna wynosić powyżej 8 znaków");
            isValid=false;
        }


    if (!digitCasePatten.matcher(password).find()) {
        passEt.requestFocus();
        passEt.setError("Hasło musi zawierać przynajmniej jedną cyfrę");
        isValid=false;
    }

}

private void ValidateEmail(String email){

    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

    Pattern emailPattern = Pattern.compile(emailRegex);

    if (!emailPattern.matcher(email).find()) {
        emailEt.requestFocus();
        emailEt.setError("Email nie jest zgodny ze wzorem");
        isValid=false;
    }


}



}