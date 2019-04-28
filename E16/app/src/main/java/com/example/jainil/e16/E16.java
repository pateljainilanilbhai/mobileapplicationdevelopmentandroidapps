package com.example.jainil.e16;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

public class E16 extends Activity
{

    FileOutputStream fos ;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e16);

        try
        {
            String destPath = "/sdcard/bsr.txt";
            File f = new File(destPath);
            if (!f.exists())
            {
                CopyDB( getBaseContext().getAssets().open("file.txt"),new FileOutputStream(destPath));
            }
            dispFile();
        }
        catch (Exception e)
        {
            Toast.makeText(E16.this, " "+e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void dispFile()
    {
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
            TextView txt=(TextView)findViewById(R.id.text);
            txt.setText(sbuffer);
        }
        catch (Exception e)
        {
            Toast.makeText(this, " "+e.toString(), Toast.LENGTH_LONG).show();
        }

    }
    public void CopyDB(InputStream inputStream,OutputStream outputStream)throws IOException
    {
        //---copy 1K bytes at a time---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) //check the length of file
        {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }
}