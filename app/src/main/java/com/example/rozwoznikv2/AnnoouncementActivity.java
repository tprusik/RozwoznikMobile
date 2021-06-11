package com.example.rozwoznikv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rozwoznikv2.DAO.User;
import com.example.rozwoznikv2.DAO.UserData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class AnnoouncementActivity extends AppCompatActivity {

    DatabaseReference userData,announcementData;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annoouncement);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");

        TextView name = (TextView) findViewById(R.id.textViewAnnouncementUserName);
        TextView sourname= (TextView) findViewById(R.id.textViewAnnouncementUserSourname);
        TextView phone = (TextView) findViewById(R.id.textViewAnnouncementUserss);
        ImageView  image = (ImageView) findViewById(R.id.imageViewAnnouncementShow);

        Button mapsButton = (Button) findViewById(R.id.buttonAnnouncementMap);

        mapsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("location", "wrocław");
                startActivity(intent);

        }
        });

        userData = FirebaseDatabase.getInstance().getReference().child("UserData").child(userID);

        userData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                UserData userData = snapshot.getValue(UserData.class);

                name.setText(userData.getName());
                phone.setText(userData.getPhone());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        storageReference = FirebaseStorage.getInstance().getReference().child("image/ss.jpg");

        try {
            File file = File.createTempFile("plik","jpg");
            storageReference.getFile(file)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(AnnoouncementActivity.this, "OKEJ", Toast.LENGTH_LONG).show();

                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            image.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AnnoouncementActivity.this, " NIE OKEJ", Toast.LENGTH_LONG).show();

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}