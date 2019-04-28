package com.example.jainil.url;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WebView wv=(WebView) findViewById(R.id.webView1);
        final Button btnGo=(Button) findViewById(R.id.button1);
        final EditText etURI=(EditText) findViewById(R.id.editText1);

        btnGo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {


                wv.loadUrl(etURI.getText().toString());
            }
        } );

    }



}