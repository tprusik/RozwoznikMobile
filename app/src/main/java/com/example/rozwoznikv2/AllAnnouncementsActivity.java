package com.example.rozwoznikv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rozwoznikv2.DAO.Announcement;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllAnnouncementsActivity extends AppCompatActivity {

    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_announcements);


        ArrayList<String> arrayList = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter;
        ArrayList<String> Ids = new ArrayList<>();

         ListView allAnouncements = (ListView) findViewById(R.id.listViewAllAnouncements);
         arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        allAnouncements.setAdapter(arrayAdapter);

        reff = FirebaseDatabase.getInstance().getReference().child("Announcement");


        reff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Announcement announcement = new Announcement();

            announcement= snapshot.getValue(Announcement.class);
            String value = announcement.getTitle();
            String userId = announcement.getUserID();
                Ids.add(userId);
                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        allAnouncements.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String userID = Ids.get(position);

                Intent intent = new Intent(getApplicationContext(),AnnoouncementActivity.class);
                intent.putExtra("userID",userID);
                startActivity(intent);

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.shareToolbar)
           // Toast.makeText(MainActivity.this,"Przycisk Toolbar", Toast.LENGTH_LONG).show();

        if(id==R.id.addAnnouncementToolbar){
            Intent intent = new Intent(getApplicationContext(),AddAnnouncementActivity.class);
            startActivity(intent);
             Toast.makeText(AllAnnouncementsActivity.this,"Przycisk Toolbar", Toast.LENGTH_LONG).show();

        }

        if(id==R.id.accountToolbar){
            Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
            startActivity(intent);
        }

        if(id==R.id.alarmToolbar){
            Intent intent = new Intent(getApplicationContext(),EmergencyCallsActivity.class);
            startActivity(intent);
        }

        return true;
    }

}