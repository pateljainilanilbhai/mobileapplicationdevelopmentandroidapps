package com.example.jainil.alarmactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;



public class MainActivity extends AppCompatActivity {




        AlarmManager alarmManager;
        private PendingIntent pendingIntent;
        private TimePicker alarmTimePicker;
        private static MainActivity inst;
        private TextView alarmTextView;

        public static MainActivity instance() {
            return inst;
        }

        @Override
        public void onStart() {
            super.onStart();
            inst = this;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
            alarmTextView = (TextView) findViewById(R.id.alarmText);
            ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        }

        public void onToggleClicked(View view) {
            if (((ToggleButton) view).isChecked()) {
                long time;
                Log.d("MyActivity", "Alarm On");
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
                Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);
                time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
                if(System.currentTimeMillis()>time)
                {
                    if (calendar.AM_PM == 0)
                        time = time + (1000*60*60*12);
                    else
                        time = time + (1000*60*60*24);
                }
                Toast.makeText(inst, "Alarm On "+time, Toast.LENGTH_SHORT).show();
                alarmManager.set(AlarmManager.RTC,time, pendingIntent);

            } else {
                alarmManager.cancel(pendingIntent);
                setAlarmText("");
                Log.d("MyActivity", "Alarm Off");
                Toast.makeText(inst, "Alarm Off", Toast.LENGTH_SHORT).show();
            }
        }


    public void add(View view) {
         {
            long time;
            Log.d("MyActivity", "Alarm On");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);
            time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
            if(System.currentTimeMillis()>time)
            {
                if (calendar.AM_PM == 0)
                    time = time + (1000*60*60*12);
                else
                    time = time + (1000*60*60*24);
            }
            Toast.makeText(inst, "Alarm On "+time, Toast.LENGTH_SHORT).show();
            alarmManager.set(AlarmManager.RTC,time, pendingIntent);

        }
    }


        public void setAlarmText(String alarmText) {
            alarmTextView.setText(alarmText);
        }
    }

