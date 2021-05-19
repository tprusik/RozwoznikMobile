package com.example.rozwoznikv2;

import androidx.fragment.app.FragmentActivity;

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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

         geocoder = new Geocoder(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {
           List<Address> adr= geocoder.getFromLocationName("żagań",1);

           if(adr.size()>0) {
               Address address = adr.get(0);



               LatLng custom = new LatLng(address.getLatitude(), address.getLongitude());

               CircleOptions copt = new CircleOptions()
                       .center(custom)
                       .radius(10000)
                       .strokeColor(Color.GREEN);
               Circle circle = mMap.addCircle(copt);

               mMap.addMarker(new MarkerOptions().position(custom).title("Marker in London"));
               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(custom, 1));


               Toast.makeText(MapsActivity.this, address.toString(), Toast.LENGTH_LONG).show();
           }

        } catch (IOException e) {
            e.printStackTrace();
        }
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        Log.i("mapa ready:","SSS");

    }
}