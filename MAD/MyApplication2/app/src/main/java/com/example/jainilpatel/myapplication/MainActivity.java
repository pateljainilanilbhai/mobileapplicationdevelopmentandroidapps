package com.example.jainilpatel.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText e;
Button b;
TextView t;
Chronometer c;
int i=1;
int count=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e=findViewById(R.id.editText);
        b=findViewById(R.id.button);
        t=findViewById(R.id.textView);
        c=findViewById(R.id.timer);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int time=Integer.parseInt(e.getText().toString());
                //Toast.makeText(MainActivity.this, String.valueOf(time), Toast.LENGTH_SHORT).show();

                c.start();
                c.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        t.setText("Your text will be displayed after "+(time-i)+" Seconds");
                        i++;
                        if(time-i==-1&&count>0)
                        {
                            Toast.makeText(MainActivity.this, "MESSAGE DISPLAYED", Toast.LENGTH_SHORT).show();
                            i=0;
                            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                            intent.putExtra("time",String.valueOf(time));
                            startActivity(intent);
                            count--;
                        }
                        if(count==0)
                        {
                            c.stop();
                        }


                    }
                });
            }
        });
    }
}
