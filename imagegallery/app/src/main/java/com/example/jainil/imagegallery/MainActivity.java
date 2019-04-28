package com.example.jainil.imagegallery;


import java.io.File;
import java.io.FileInputStream;


import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        try
        {

            String[] projection={Media.DISPLAY_NAME, Media.DATA, Media.SIZE};
            Cursor c=managedQuery(Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
            ImageView ivImage=(ImageView)findViewById(R.id.myImg);
            File file = null;
            String imgName = null ;

            if(c.getCount()>0)
            {
                while(c.moveToNext())
                {
                    file=new File(c.getString(1)); //after completing loop, it take last image from native Gallery to display.
                    imgName=c.getString(0);
                }
                c.close();
                FileInputStream fis=new FileInputStream(file);
                byte[] buffer=new byte[fis.available()];
                fis.read(buffer);
                Bitmap bm=BitmapFactory.decodeByteArray(buffer, 0, buffer.length);

                TextView txtTitle=(TextView)findViewById(R.id.txtTitle);
                txtTitle.setText(imgName.toString());
                ivImage.setImageBitmap(bm);
            }
        }
        catch(Exception e)
        {
            Toast.makeText(MainActivity.this, "Error: "+e, Toast.LENGTH_LONG).show();
        }
    }
}
