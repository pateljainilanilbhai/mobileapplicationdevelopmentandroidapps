package com.example.jainil.fileinsdcard;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;



import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        try
        {
            File fileDir = Environment.getExternalStorageDirectory();
            File directory = new File(fileDir.getAbsolutePath());
            File file = new File(directory , "jainil.txt");
            FileInputStream fis = new FileInputStream(file);
            String str = null;
            StringBuffer sbuffer = new StringBuffer();
            DataInputStream dataio = new DataInputStream(fis);
            while((str = dataio.readLine()) != null)
            {
                sbuffer.append(str + "\n");
            }
            TextView txt=(TextView)findViewById(R.id.showTxt);
            txt.setText(sbuffer);
        }
        catch (Exception e)
        {
            Toast.makeText(this, " "+e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}

