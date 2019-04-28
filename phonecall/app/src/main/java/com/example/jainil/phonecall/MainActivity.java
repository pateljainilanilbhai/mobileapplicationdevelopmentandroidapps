package com.example.jainil.phonecall;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    Button btnCall;
    EditText txtNo;

    // @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnCall = (Button) findViewById(R.id.button);
        txtNo = (EditText) findViewById(R.id.editText);
        btnCall.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

                if (txtNo.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Enter Number", Toast.LENGTH_LONG).show();
                    txtNo.requestFocus();
                } else {
                    try {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + txtNo.getText().toString()));
                        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(callIntent);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(MainActivity.this,  " "+e.toString(), Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

    }
    //@Override
    public void onDestroy()
    {
        super.onDestroy();

    }
}
