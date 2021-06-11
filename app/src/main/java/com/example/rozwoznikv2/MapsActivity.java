package com.example.rozwoznikv2;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Aktywność korzytająca z API google maps i wyswietlająca lokalizację użytkownika w przybliżeniu .
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    /**
     *
     */
    private GoogleMap mMap;
    /**
     * Serwis podający współrzedne geogradiczne na podstawie podanej nazwy lokacji.
     */
    private Geocoder geocoder;
    /**
     * String z lokalizacją użytkownika.
     */
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

         geocoder = new Geocoder(this);

        Intent intent = getIntent();
        location  = intent.getStringExtra("location");

    }


    /**
     * Funcja umożliwiająca edytowanie pola google maps przykładowo postawienie znacznika w miejscu lokalizacji.
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {
           List<Address> adr= geocoder.getFromLocationName(location,1);

           if(adr.size()>0) {
               Address address = adr.get(0);



               LatLng custom = new LatLng(address.getLatitude(), address.getLongitude());

               CircleOptions copt = new CircleOptions()
                       .center(custom)
                       .radius(100)
                       .strokeColor(Color.GREEN);
               Circle circle = mMap.addCircle(copt);

               mMap.addMarker(new MarkerOptions().position(custom).title("Marker in London"));
               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(custom, 1));


               Toast.makeText(MapsActivity.this, address.toString(), Toast.LENGTH_LONG).show();
           }

        } catch (IOException e) {
            e.printStackTrace();
        }

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));


        Log.i("mapa ready:","SSS");

    }
}