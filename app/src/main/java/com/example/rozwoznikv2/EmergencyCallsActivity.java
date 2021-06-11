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

/**
 * Aktywność umożliwiająca wykonywanie połączeń alarmowych oraz odczytywanie statystyk dotyczących Covid 19 z oficjalnej strony rządowej.
 */
public class EmergencyCallsActivity extends AppCompatActivity {

    /**
     * 
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_calls);


        /**
         * Button umożliwiający wykonanie połączenia z pogotowiem ratunkowym.
         */
        Button medicalbutton = (Button) findViewById(R.id.btnMedical);

        medicalbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:999"));
                startActivity(callIntent);
            }
        });

        /**
         * Button umożliwiający wykonanie połączenia z policją.
         */

        Button policebutton = (Button) findViewById(R.id.btnPolice);

        policebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:997"));
                startActivity(callIntent);
            }
        });

        /**
         * Button umożliwiający wykonanie połączenia ze strażą pożarną.
         */

        Button fireBrigadeButton = (Button) findViewById(R.id.btnFireBrigade);

        fireBrigadeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:998"));
                startActivity(callIntent);
            }
        });

        /**
         * tekst - link do oficjalnej strony rządowej- https://www.gov.pl/web/koronawirus.

         */

        TextView textViewCovid = (TextView) findViewById(R.id.textViewCovidRaport);

        textViewCovid.setMovementMethod(LinkMovementMethod.getInstance());

    }
}