package com.example.rozwoznikv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rozwoznikv2.DAO.Announcement;
import com.example.rozwoznikv2.DAO.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddAnnouncementActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseReference reff;
    public FirebaseStorage storage;
    public StorageReference storageReference;
    String title,description,category;
    ImageView imageViewCamera;
    public Uri imageUri;
    Button cameraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

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

        askPermission();
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


        addAnnouncement .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                title = titleEt.getText().toString();
                description = descriptionEt.getText().toString();

                announcement.setTitle(title);
                announcement.setCategory(category);
                announcement.setDescription(description);
                announcement.setUserID(userID);
                reff.push().setValue(announcement);

                uploadImage();

                Toast.makeText(AddAnnouncementActivity.this,"SUKCES ",Toast.LENGTH_LONG).show();

            }


        });


        imageViewCamera =(ImageView) findViewById(R.id.imageViewAnnouncementPhtoto) ;
         cameraButton = (Button) findViewById(R.id.addAnnouncementPhotoButton);

        if (ContextCompat.checkSelfPermission(AddAnnouncementActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AddAnnouncementActivity.this,
                    new String[]
                            {
                                    Manifest.permission.CAMERA
                            },100);
        }

        cameraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              //  Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               // startActivityForResult(intent,100);

            }
        });

    }

    private void setView() {
       cameraButton.setOnClickListener(v -> {
            String fileName = "new-photo-name.jpg";
            // Create parameters for Intent with filename
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, fileName);
            values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera");
            imageUri =
                    getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            values);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, 1231);
        });
    }


    private void uploadImage(){

        StorageReference mountainsRef = storageReference.child("image/ss.jpg");
        mountainsRef.putFile(imageUri);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* if(requestCode==100){

            Bitmap captureData = (Bitmap) data.getExtras().get("data");
            imageViewCamera.setImageBitmap(captureData);
            imageUri = data.getData();*/

        //}


        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1231 && resultCode == Activity.RESULT_OK) {
            try {
                ContentResolver cr = getContentResolver();
                try {
                    // Creating a Bitmap with the image Captured
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(cr, imageUri);
                    // Setting the bitmap as the image of the
                    imageViewCamera.setImageBitmap(bitmap);
                } catch ( IOException e) {
                    e.printStackTrace();
                }
            } catch (IllegalArgumentException e) {
                if (e.getMessage() != null)
                    Log.e("Exception", e.getMessage());
                else
                    Log.e("Exception", "Exception");
                e.printStackTrace();
            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    // Asking user for storage permission
    public void askPermission() {
        // Checking if the permissions are not granted.
        if (
                ContextCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(
                                this,
                                android.Manifest.permission.READ_EXTERNAL_STORAGE
                        ) != PackageManager.PERMISSION_GRANTED
        ) {
            // If not granted requesting Read and  Write storage
            ActivityCompat.requestPermissions(this, /*You can ask for multiple request by adding
            more permissions to the string*/new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 60);
        } else {
            // If permissions are granted we proceed by setting an OnClickListener for the button
            // which helps the user pick the image
            setView();
        }
    }

    // This method is called after the permissions have been asked i.e. the dialog that says
    // allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Now by default we assume that the permission has not been granted
        boolean allPermissionsGranted = false;
        // Now we check if the permission was granted
        if ( requestCode == 60 && grantResults.length > 0) {
            // If all the permissions are granted allPermissionsGranted is set to true else false
            allPermissionsGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED;
        }
        // If permissions are granted we call the setView Method which prompts the user to pick
        // an Image either by the clicking it now or picking from the gallery
        if ( allPermissionsGranted ) {
            setView();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        category = parent.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}