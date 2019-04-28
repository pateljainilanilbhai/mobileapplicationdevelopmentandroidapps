package com.example.jainil.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class seecompany extends AppCompatActivity {


    String username;
    ArrayList<String> ar;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seecompany);
        ar=new ArrayList<String>();
        username=getIntent().getStringExtra("username");
//        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        lv=(ListView)findViewById(R.id.seecompany);
        Query q= FirebaseDatabase.getInstance().getReference("Enrolment");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    if(d.getValue(enrollment.class).username.equals(username)){
                    enrollment e=d.getValue(enrollment.class);
                    ar.add(e.getCompanyid());}
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(seecompany.this,android.R.layout.simple_list_item_1,android.R.id.text1,ar);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent=new Intent(seecompany.this,seeenrolledcompany.class);
                        intent.putExtra("username",username);
                        intent.putExtra("company",ar.get(i));
                        startActivity(intent);
                    }
                });

//                seecompanylist adapter=new seecompanylist(seecompany.this,ar);
//                lv.setAdapter(adapter);
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
