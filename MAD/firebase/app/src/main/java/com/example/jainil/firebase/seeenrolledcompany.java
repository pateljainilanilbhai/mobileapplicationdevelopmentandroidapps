package com.example.jainil.firebase;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class seeenrolledcompany extends AppCompatActivity {
    String companyname;
    String username;
    TextView location;
    TextView name;
    TextView website;
    TextView requirements;
    TextView date;
    TextView time;
    TextView round1location;
    TextView round2location;
    TextView round3location;
    TextView round1result;
    TextView round2result;
    TextView round3result;
    TextView round1timing;
    TextView round2timing;
    TextView round3timing;
    Button round1attendance;
    Button round2attendance;
    Button round3attendance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeenrolledcompany);
        companyname=getIntent().getStringExtra("company");
        username=getIntent().getStringExtra("username");
         location=(TextView)findViewById(R.id.textView9);
         name=(TextView)findViewById(R.id.textView7);
         website=(TextView)findViewById(R.id.textView10);
         requirements=(TextView)findViewById(R.id.textView11);
         date=(TextView)findViewById(R.id.textView12);
         time=(TextView)findViewById(R.id.textView13);
         round1location=(TextView)findViewById(R.id.textView14);
         round2location=(TextView)findViewById(R.id.textView18);
         round3location=(TextView)findViewById(R.id.textView21);
         round1result=(TextView)findViewById(R.id.textView17);
         round2result=(TextView)findViewById(R.id.textView20);
         round3result=(TextView)findViewById(R.id.textView37);
        round1timing=(TextView)findViewById(R.id.textView16);
        round2timing=(TextView)findViewById(R.id.textView19);
        round3timing=(TextView)findViewById(R.id.textView22);
        round1attendance=(Button)findViewById(R.id.button12);
        round2attendance=(Button)findViewById(R.id.button11);
        round3attendance=(Button)findViewById(R.id.button6);



        round1attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(seeenrolledcompany.this,qrcode.class);
                i.putExtra("user",username.toString());
                i.putExtra("company",companyname);
                i.putExtra("round","1");
                startActivity(i);
            }
        });

        round2attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(seeenrolledcompany.this,qrcode.class);
                i.putExtra("user",username);
                i.putExtra("company",companyname);
                i.putExtra("round","2");
                startActivity(i);
            }
        });


        round3attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(seeenrolledcompany.this,qrcode.class);
                i.putExtra("user",username);
                i.putExtra("company",companyname);
                i.putExtra("round","3");
                startActivity(i);
            }
        });

    }


    protected void onStart() {
        super.onStart();
        Query q= FirebaseDatabase.getInstance().getReference("Company/"+companyname);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Company company =dataSnapshot.getValue(Company.class);
                 location.setText(company.getLocation());
                 name.setText(company.getName());
                 website.setText(company.getWebsite());
                 requirements.setText(company.getRequirements());
                 date.setText(company.getDate());
                 time.setText(company.getTime());
                 round1location.setText(company.getRound1location());
                 round2location.setText(company.getRound2location());
                 round3location.setText(company.getRound3location());
                 round1timing.setText(company.getRound1time());
                 round2timing.setText(company.getRound2time());
                 round3timing.setText(company.getRound3time());
                Intent intent = new Intent(seeenrolledcompany.this, backgroundresult.class);
                intent.putExtra("username",username);
                intent.putExtra("companyname",companyname);
                intent.putExtra("round","1");
                startService(intent);


                Intent intent2 = new Intent(seeenrolledcompany.this, backgroundresult2.class);
                intent2.putExtra("username",username);
                intent2.putExtra("companyname",companyname);
                intent2.putExtra("round","2");
                startService(intent2);


                Intent intent3 = new Intent(seeenrolledcompany.this, backgroundresult3.class);
                intent3.putExtra("username",username);
                intent3.putExtra("companyname",companyname);
                intent3.putExtra("round","3");
                startService(intent3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Query qq= FirebaseDatabase.getInstance().getReference("Enrolment/"+username+companyname);
        qq.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                enrollment e =dataSnapshot.getValue(enrollment.class);

                round1result.setText(e.getRound1result());
                round2result.setText(e.getRound2result());
                round3result.setText(e.getRound3result());
               
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
