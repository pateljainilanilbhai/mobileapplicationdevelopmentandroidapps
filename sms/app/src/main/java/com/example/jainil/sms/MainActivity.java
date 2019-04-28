package com.example.jainil.sms;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;



public class MainActivity extends Activity
{


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TextView view = new TextView(this);
        Uri uriSMS = Uri.parse("content://sms/inbox");
        Cursor c = getContentResolver().query(uriSMS, null, null, null,null);
        String sms = "";
        while (c.moveToNext()) {
            sms += "From :" + c.getString(2) + " : " + c.getString(12)+"\n";
        }
        view.setText(sms);
        setContentView(view);
    }
}