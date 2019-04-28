package com.example.jainil.attendance;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.example.jainil.attendance.BackgroundTask;
import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission_group.CAMERA;

//import android.support.v4.app.ActivityCompat;

//import android.support.v4.app.ActivityCompat;

public class qrcode extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                //Toast.makeText(qrcode.this,"Permission Granted.",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            requestPermission();
        }
    }

    private boolean checkPermission()
    {
        return (ContextCompat.checkSelfPermission(qrcode.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
    }
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted){
                        Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and camera", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
       // if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if(scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
          //  } else {
            //    requestPermission();
            //}
        }
    }

   /* @Override
    public void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }*/


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(qrcode.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
    @Override
    public void handleResult(Result result) {
        try{
            int flag=0;
            final String myResult = result.getText();
            String[] parts = myResult.split(Pattern.quote(","));
            parts = parts[0].split(Pattern.quote("/"));
            int check1 = Integer.parseInt(parts[0]);
            int check2=Integer.parseInt(parts[1]);
            int check3=Integer.parseInt(parts[2]);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String currentDateandTime = sdf.format(new Date());
            String F = currentDateandTime.substring(4,6);

            int res1 = Integer.parseInt(F);
            F = currentDateandTime.substring(6, 8);
            int res2 = Integer.parseInt(F);
            F = currentDateandTime.substring(0, 4);
            int res3 = Integer.parseInt(F);

            // Toast.makeText(MainActivity.this,myResult+" "+res,Toast.LENGTH_LONG).show();
            if(check1==res1 && check2==res2 && check3==res3)
            {
                String[] Result = myResult.split(Pattern.quote(","));
                parts = Result[1].split(Pattern.quote(":"));
                //Toast.makeText(MainActivity.this,myResult+" "+currentDateandTime,Toast.LENGTH_LONG).show();
                check1 = Integer.parseInt(parts[0]);
                check2 = Integer.parseInt(parts[1]);
                check3 = Integer.parseInt(parts[2]);

                F = currentDateandTime.substring(9,11);
                res1 = Integer.parseInt(F);
                F = currentDateandTime.substring(11,13);
                res2 = Integer.parseInt(F);
                F = currentDateandTime.substring(13);
                res3 = Integer.parseInt(F);

                if(check1==res1 && check2==res2)
                {
                    if(res3-check3<=10)
                    {
                        flag=1;
                        //String Pass=getIntent().getStringExtra("user").toString();
                        String Pass=getIntent().getStringExtra("user").toString();
                        Toast.makeText(qrcode.this, "Welcome", Toast.LENGTH_LONG).show();
                        //Toast.makeText(qrcode.this,Pass,Toast.LENGTH_LONG).show();
                        Intent i=new Intent(qrcode.this,success.class);
                        i.putExtra("user",Pass);
                        startActivity(i);

                    }
                }
                else
                {
                    flag=1;
                    Toast.makeText(qrcode.this, "Barcode is Expired, Scan Again.", Toast.LENGTH_LONG).show();
                    Intent i=new Intent (qrcode.this,qrcode.class);
                    i.putExtra("user",getIntent().getStringExtra("user").toString());
                    startActivity(i);
                }
            }
            if(flag==0){
                Toast.makeText(qrcode.this,"Wrong",Toast.LENGTH_LONG).show();
                Intent i=new Intent (qrcode.this,qrcode.class);
                i.putExtra("user",getIntent().getStringExtra("user").toString());
                startActivity(i);
            }
        }
        catch(Exception E)
        {
            Toast.makeText(qrcode.this,"Wrong",Toast.LENGTH_LONG).show();
            Intent i=new Intent (qrcode.this,qrcode.class);
            i.putExtra("user",getIntent().getStringExtra("user").toString());
            startActivity(i);
        }
        Log.d("QRCodeScanner", result.getText());
        Log.d("QRCodeScanner", result.getBarcodeFormat().toString());
    }
}
