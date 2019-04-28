package com.example.jainil.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    ImageView iv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=(ImageView)findViewById(R.id.imageView1);
        Button b =(Button)findViewById(R.id.button1);
        b.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, 0);
            }
        });
    }
    protected void OnActivityResult(int requestCode,int resultcode,Intent data)
    {
        Bitmap bm=(Bitmap)data.getExtras().get("data");
        iv.setImageBitmap(bm);
    }
}