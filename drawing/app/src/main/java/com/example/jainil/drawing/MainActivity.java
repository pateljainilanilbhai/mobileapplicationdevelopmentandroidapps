package com.example.jainil.drawing;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(new ExploreTouchEvent(this, null));
    }
}
