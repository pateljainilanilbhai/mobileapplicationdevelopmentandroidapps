package com.example.jainil.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewround2resultlist extends AppCompatActivity {

    ListView listViewartists;
    Query query;
    List<enrollment> ar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewround1resultlist);
        listViewartists=(ListView)findViewById(R.id.round1resultsaving);
        ar=new ArrayList<>();
        query = FirebaseDatabase.getInstance().getReference("Enrolment");
    }


    @Override
    protected void onStart() {
        super.onStart();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ar.clear();
                for(DataSnapshot artistSnapshot:dataSnapshot.getChildren())
                {
                    enrollment e =artistSnapshot.getValue(enrollment.class);
                    if(e.companyid.equals(getIntent().getStringExtra("companyname").toString())&&e.round1result.equals("Pass"))
                        ar.add(e);
                }

                round2resultenterlist adapter=new round2resultenterlist(viewround2resultlist.this,ar);
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
