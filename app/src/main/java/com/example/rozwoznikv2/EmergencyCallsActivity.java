package com.example.rozwoznikv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmergencyCallsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_calls);



        Button medicalbutton = (Button) findViewById(R.id.btnMedical);
        medicalbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:123456789"));
                startActivity(callIntent);
            }
        });

        TextView textViewCovid = (TextView) findViewById(R.id.textViewCovidRaport);
        textViewCovid.setMovementMethod(LinkMovementMethod.getInstance());

    }
}