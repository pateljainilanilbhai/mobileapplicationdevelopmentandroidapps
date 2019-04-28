package com.example.jainil.attendance;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class success extends Activity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        btn = findViewById(R.id.myButton);
        String Pass = getIntent().getStringExtra("user").toString();
        Toast.makeText(success.this,Pass,Toast.LENGTH_LONG).show();
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(Pass);

//        btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                String Pass = getIntent().getStringExtra("user").toString();
//                Toast.makeText(success.this,Pass,Toast.LENGTH_LONG).show();
//                BackgroundTask backgroundTask = new BackgroundTask(success.this);
//                backgroundTask.execute(Pass);
//                //  finish();
//            }
//        });
    }

    }
