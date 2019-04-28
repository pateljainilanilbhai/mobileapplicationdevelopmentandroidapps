package com.example.jainil.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText location;
    EditText name;
    EditText website;
    EditText requirements;
    EditText date;
    EditText time;
    EditText round1location;
    EditText round2location;
    EditText round3location;
    EditText round1time;
    EditText round2time;
    EditText round3time;


    Button b;
    Button b1;
   // Button next;

    ListView listViewartists;
    DatabaseReference databaseartist;
    List<Company> ar;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        location=(EditText)findViewById(R.id.editText);
        name=(EditText)findViewById(R.id.editText2);
        website=(EditText)findViewById(R.id.editText3);
        requirements=(EditText)findViewById(R.id.editText4);
        date=(EditText)findViewById(R.id.editText5);
        time=(EditText)findViewById(R.id.editText6);


        round1location=(EditText)findViewById(R.id.editText10);
        round2location=(EditText)findViewById(R.id.editText8);
        round3location=(EditText)findViewById(R.id.editText13);



        round1time=(EditText)findViewById(R.id.editText9);
        round2time=(EditText)findViewById(R.id.editText14);
        round3time=(EditText)findViewById(R.id.editText12);


        b=(Button)findViewById(R.id.button);
 //       next=(Button)findViewById(R.id.button3);
        b1=(Button)findViewById(R.id.button4);



//        listViewartists=(ListView)findViewById(R.id.listviewaritst);
//        ar=new ArrayList<>();


        databaseartist = FirebaseDatabase.getInstance().getReference("Company");

//                next.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent i=new Intent(MainActivity.this,ViewingData.class);
//                        startActivity(i);
//                    }
//                });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCompany();

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,viewcompanyname.class);
                i.putExtra("companyname",name.getText().toString());
                startActivity(i);
            }
        });


    }

  //  @Override
//    protected void onStart() {
//        super.onStart();
//
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ar.clear();
//                for(DataSnapshot artistSnapshot:dataSnapshot.getChildren())
//                {
//                    Company artist=artistSnapshot.getValue(Company.class);
//                    ar.add(artist);
//                }
//
//                Artistlist adapter=new Artistlist(MainActivity.this,ar);
//                listViewartists.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
       // });

   // }

    private void addCompany(){
        String location=this.location.getText().toString().trim();
        String name=this.name.getText().toString().trim();
        String website=this.website.getText().toString().trim();
        String requirement=this.requirements.getText().toString().trim();
        String date=this.date.getText().toString().trim();
        String time=this.time.getText().toString().trim();

        String round1location=this.round1location.getText().toString().trim();
        String round2location=this.round2location.getText().toString().trim();
        String round3location=this.round3location.getText().toString().trim();

        String round1time=this.round1time.getText().toString().trim();
        String round2time=this.round2time.getText().toString().trim();
        String round3time=this.round3time.getText().toString().trim();

        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(location)&&!TextUtils.isEmpty(website)&&!TextUtils.isEmpty(requirement)&&!TextUtils.isEmpty(date)&&!TextUtils.isEmpty(time))
        {
            String id=databaseartist.push().getKey();
            Company company =new Company(id,location,name,website,requirement,date,time,round1location,round2location,round3location,round1time,round2time,round3time);
            databaseartist.child(name).setValue(company);
            Toast.makeText(this, "Company ADDED", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Fill Complete details", Toast.LENGTH_SHORT).show();
        }

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
