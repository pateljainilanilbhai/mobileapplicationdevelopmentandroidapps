package com.example.jainil.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Teacheroptions extends AppCompatActivity {


    String companyname;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacheroptions);
        b1=(Button)findViewById(R.id.button5);
        b2=(Button)findViewById(R.id.button7);
        b3=(Button)findViewById(R.id.button8);
        b4=(Button)findViewById(R.id.button9);
        b5=(Button)findViewById(R.id.button13);
        b6=(Button)findViewById(R.id.button14);
        b7=(Button)findViewById(R.id.button15);
        companyname=getIntent().getStringExtra("companyname");


        b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(Teacheroptions.this,viewinguserenrol.class);
            i.putExtra("companyname",companyname);
            startActivity(i);
        }
    });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Teacheroptions.this,viewround1resultlist.class);
                i.putExtra("companyname",companyname);
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Teacheroptions.this,viewround2resultlist.class);
                i.putExtra("companyname",companyname);
                startActivity(i);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Teacheroptions.this,viewround3resultlist.class);
                i.putExtra("companyname",companyname);
                startActivity(i);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Teacheroptions.this,viewround1attendancelist.class);
                i.putExtra("companyname",companyname);
                startActivity(i);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Teacheroptions.this,viewround2attendancelist.class);
                i.putExtra("companyname",companyname);
                startActivity(i);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Teacheroptions.this,viewround3attendancelist.class);
                i.putExtra("companyname",companyname);
                startActivity(i);
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


