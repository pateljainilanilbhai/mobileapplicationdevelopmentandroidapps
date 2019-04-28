package com.example.jainil.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewround2attendancelist extends AppCompatActivity {
    String companyname;
    ArrayList<String> ar;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewround2attendancelist);
        companyname=getIntent().getStringExtra("companyname");
        ar=new ArrayList<String>();
        lv=(ListView)findViewById(R.id.round2atttendancelist);
        Query q= FirebaseDatabase.getInstance().getReference("Enrolment").endAt(companyname);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d:dataSnapshot.getChildren()
                        ) {
                    enrollment e=d.getValue(enrollment.class);
                    if(e.round2attendance.equals("present"))
                    {
                        ar.add(e.username);
                    }

                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(viewround2attendancelist.this, android.R.layout.simple_list_item_1, android.R.id.text1, ar);
                lv.setAdapter(arrayAdapter);

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
