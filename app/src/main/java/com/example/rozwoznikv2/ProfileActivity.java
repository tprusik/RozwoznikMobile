package com.example.rozwoznikv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.rozwoznikv2.DAO.Address;
import com.example.rozwoznikv2.DAO.User;
import com.example.rozwoznikv2.DAO.UserData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    EditText nameEt,phoneEt,emailEt,addressEt;
    DatabaseReference reffData, reffAddress;

    String name = "test",phone="test",address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("com.example.notatnikfinal", Context.MODE_PRIVATE);
        String email = prefs.getString("email","def");
        String userID = prefs.getString("userID","def");


        reffData = FirebaseDatabase.getInstance().getReference().child("UserData").child(userID);
        reffAddress = FirebaseDatabase.getInstance().getReference().child("Address").child(userID);


        nameEt = (EditText) findViewById(R.id.editTextProfileName);
        phoneEt = (EditText) findViewById(R.id.editTextProfilePhone);
        emailEt = (EditText) findViewById(R.id.editTextTextProfileEmail);
        addressEt = (EditText) findViewById(R.id.editTextProfileAddress);

        reffData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()) {

                    UserData userData = snapshot.getValue(UserData.class);

                    nameEt.setText(userData.getName());
                    phoneEt.setText(userData.getPhone());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reffAddress.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()) {
                    Address address = snapshot.getValue(Address.class);

                }
                else
                {

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        emailEt.setText(userID);
        addressEt.setText("test");

       // addressEt.setText(address.getCity() + " "+ address.getZipCode());


    }
}