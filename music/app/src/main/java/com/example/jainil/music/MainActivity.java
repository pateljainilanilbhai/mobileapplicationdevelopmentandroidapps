package com.example.jainil.music;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends Activity {

    MediaPlayer player=null;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start=(Button)findViewById(R.id.button1);
        Button stop=(Button)findViewById(R.id.button2);
        getSystemService(Context.AUDIO_SERVICE);
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0)
            {

                try
                {
                    player=null;
                    player=new MediaPlayer();
                    String audioFilePath="/sdcard/a.mid";
                    player.setDataSource(audioFilePath);
                    player.prepare();
                    player.start();
                }
                catch(Exception e)
                {
                    Toast.makeText(MainActivity.this,""+e,Toast.LENGTH_LONG).show();

                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                player.stop();
            }
        });
    }
}