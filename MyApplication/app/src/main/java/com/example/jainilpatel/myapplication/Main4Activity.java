package com.example.jainilpatel.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner s;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        s=(Spinner)findViewById(R.id.spinner);
        im=(ImageView)findViewById(R.id.imageView);
        s.setOnItemSelectedListener(this);

        // Spinner Drop down elements


        // Creating adapter for spinner
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this,R.array.country,R.layout.support_simple_spinner_dropdown_item);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        s.setAdapter(dataAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    switch(position)
    {
        case 0:
            im.setImageResource(R.drawable.ic_launcher_background);
            break;
        case 1:
            im.setImageResource(R.drawable.ic_launcher_foreground);
            break;
        case 2:
            im.setImageResource(R.drawable.ic_launcher_background);
            break;
        case 3:
            im.setImageResource(R.drawable.ic_launcher_foreground);
            break;
        case 4:
            im.setImageResource(R.drawable.ic_launcher_background);
            break;
        case 5:
            im.setImageResource(R.drawable.ic_launcher_foreground);
            break;
        case 6:
            im.setImageResource(R.drawable.ic_launcher_background);
            break;
    }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
