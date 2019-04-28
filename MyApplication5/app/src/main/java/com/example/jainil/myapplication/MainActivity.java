package com.example.jainil.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.String;

public class MainActivity extends AppCompatActivity {

    Button b1;
    EditText t1;
    EditText t2;
    EditText t3;
    EditText t4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button);
        t1=(EditText)findViewById(R.id.editText5);
        t2=(EditText)findViewById(R.id.editText6);
        t3=(EditText)findViewById(R.id.editText8);
        t4=(EditText)findViewById(R.id.editText10);


    }
    public void btnclick(View v)
    {

        String s1=t1.getText().toString();
        String s2=t2.getText().toString();
        String s3=t3.getText().toString();
        String s4=t4.getText().toString();

        if(s2.endsWith("@charusat.edu.in"))
        {
            Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL" , Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this,Main2Activity.class);
            i.putExtra("parcel_data", s1);
            startActivity(i);
        }
        else
        {
            Toast.makeText(MainActivity.this, "LOGIN UNSUCCESSFUL" , Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(MainActivity.this, s1+" "+s2+ " "+ s3 +" "+s4 , Toast.LENGTH_SHORT).show();
    }
}
