package com.example.rozwoznikv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rozwoznikv2.DAO.Announcement;
import com.example.rozwoznikv2.DAO.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAnnouncementActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseReference reff;
    String title,description,category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);


        SharedPreferences prefs = getApplicationContext().getSharedPreferences("com.example.notatnikfinal", Context.MODE_PRIVATE);

       String userID =  prefs.getString("userID","0");
       boolean isLogin = prefs.getBoolean("Islogin",false);

        EditText descriptionEt = (EditText) findViewById(R.id.edit_description_addAnnouncement);
        EditText titleEt = (EditText) findViewById(R.id.edit_title_addAnnouncement);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerAddAnnouncement);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Kategoria, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Announcement announcement = new Announcement();
        reff = FirebaseDatabase.getInstance().getReference().child("Announcement");

        Button addAnnouncement = (Button) findViewById(R.id.addAnnouncementButton);
       addAnnouncement .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                 title = titleEt.getText().toString();
                 description = descriptionEt.getText().toString();

                 announcement.setTitle(title);
                 announcement.setCategory(category);
                 announcement.setDescription(description);
                 announcement.setUserID(userID);
                 reff.push().setValue(announcement);

                Toast.makeText(AddAnnouncementActivity.this,"SUKCES ",Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        category = parent.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}