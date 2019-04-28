package com.example.jainil.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewcompanyname extends AppCompatActivity {

    ListView listViewartists;
    DatabaseReference databaseartist;
    List<Company> ar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcompanyname);
        listViewartists=(ListView)findViewById(R.id.namelist);
        ar=new ArrayList<>();
        databaseartist = FirebaseDatabase.getInstance().getReference("Company");


    }


    @Override
    protected void onStart() {
        super.onStart();

        databaseartist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ar.clear();
                for(DataSnapshot artistSnapshot:dataSnapshot.getChildren())
                {
                    Company company =artistSnapshot.getValue(Company.class);
                    ar.add(company);
                }

                companynamelist adapter=new companynamelist(viewcompanyname.this,ar);
                listViewartists.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }



}
