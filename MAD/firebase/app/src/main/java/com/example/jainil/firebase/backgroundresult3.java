package com.example.jainil.firebase;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class backgroundresult3 extends Service {
    String username;
    String companyname;
    String round;
    public backgroundresult3() {

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {

        super.onCreate();



    }


    @Override
    public int onStartCommand (Intent intent, int flags, int startId)
    {
        super.onStartCommand(intent, flags, startId);
        username=intent.getStringExtra("username");
        companyname=intent.getStringExtra("companyname");
        round=intent.getStringExtra("round");
//        Toast.makeText(this, username+" "+companyname+" "+round+" OK", Toast.LENGTH_LONG).show();
        Query qq = FirebaseDatabase.getInstance().getReference("Enrolment/" + username + companyname);
        qq.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                enrollment e = dataSnapshot.getValue(enrollment.class);


                if (e.getRound3result().equals("Pass")) {
                    NotificationHelper helper = new NotificationHelper(backgroundresult3.this);
                    helper.createNotification("Charusat", "Congratulation You have passed round " + round + " of " + companyname);
                    stopSelf();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        Toast.makeText(this, "Invoke background service onDestroy method.", Toast.LENGTH_LONG).show();
    }
}
