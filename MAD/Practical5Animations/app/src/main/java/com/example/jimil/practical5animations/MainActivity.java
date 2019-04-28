package com.example.jimil.practical5animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner spnr;
    ImageView imgmaster;
    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnr=(Spinner) findViewById(R.id.spinner);
        imgmaster=(ImageView) findViewById(R.id.imageView);

        adapter = ArrayAdapter.createFromResource(this,R.array.Animation,android.R.layout.simple_spinner_item);
        spnr.setAdapter(adapter);
        spnr.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
        if(spnr.getSelectedItem().equals("Alpha")){
            anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
        }
        else if(spnr.getSelectedItem().equals("Rotate")){
            anim = AnimationUtils.loadAnimation(this,R.anim.rotate);
        }
        else if(spnr.getSelectedItem().equals("Scale")){
            anim = AnimationUtils.loadAnimation(this,R.anim.scale);
        }
        else if(spnr.getSelectedItem().equals("Spin")){
            anim = AnimationUtils.loadAnimation(this,R.anim.spin);
        }
        else if(spnr.getSelectedItem().equals("Translate")){
            anim = AnimationUtils.loadAnimation(this,R.anim.translate);
        }
        imgmaster.startAnimation(anim);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
