package com.example.jainil.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import java.util.logging.Handler;

public class AlarmReceiver extends BroadcastReceiver
{
   Ringtone r;
    @Override
    public void onReceive(Context context, Intent intent)
    {
//        MainActivity inst = MainActivity.instance();
//        inst.setAlarmText("Alarm! Wake up! Wake up!");
        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();
        r=ringtone;

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                r.stop();
            }
        }, 10000);
    }
}