package com.example.rozwoznikv2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Aktywność umożliwiająca wykonywanie zdjęć produktom
 */
public class CameraActivity extends AppCompatActivity {

    /**
     * Przycisk przekierowyjący do aparatu.
     */

    Button cameraButton;

    /**
     * ImageView z wykonanym zdjęciem.
     */
    ImageView imageViewCamera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageViewCamera =(ImageView) findViewById(R.id.imageViewPhoto) ;
        cameraButton = (Button) findViewById(R.id.btnTakePhoto);

        if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(CameraActivity.this,
            new String[]
                    {
                            Manifest.permission.CAMERA
                    },100);
        }


        cameraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /**
                 * Intencja przekierowująca do okna aparatu
                 */
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent,100);
            }
        });



    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     * Funkcja która umożliwia wklejenie zwróconego z aparatu zdjęcia do ImagreView
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            Bitmap captureData = (Bitmap) data.getExtras().get("data");
            imageViewCamera.setImageBitmap(captureData);
        }
    }
}