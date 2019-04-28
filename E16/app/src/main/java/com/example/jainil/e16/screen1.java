package com.example.jainil.e16;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

public class screen1 extends Activity
{
    /**  @author Bipin S Rupadiya , www.bipinrupadiya.com
     */

    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.activity_screen1);
        try
        {
            File fileDir = Environment.getExternalStorageDirectory();
            File directory = new File(fileDir.getAbsolutePath());
            File file = new File(directory , "bsr.txt");
            FileInputStream fis = new FileInputStream(file);
            String str = null;
            StringBuffer sbuffer = new StringBuffer();
            DataInputStream dataio = new DataInputStream(fis);
            while((str = dataio.readLine()) != null)
            {
                sbuffer.append(str + "\n");
            }
            TextView txt=(TextView)findViewById(R.id.textView);
            txt.setText(sbuffer);
        }
        catch (Exception e)
        {
            Toast.makeText(this, " "+e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}

