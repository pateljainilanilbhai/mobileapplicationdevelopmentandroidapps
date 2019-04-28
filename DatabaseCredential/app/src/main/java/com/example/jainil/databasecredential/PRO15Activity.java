package com.example.jainil.databasecredential;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PRO15Activity extends Activity implements OnItemClickListener {

    SQLiteDatabase db;
    ListView lv;
    Button insert;
    Button update;
    Button delete;

    @SuppressLint("WrongConstant")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro15);
        db=openOrCreateDatabase("database1",SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db.execSQL("create table if not exists stud(id integer primary key AUTOINCREMENT,name text,age integer)");
        lv =(ListView) findViewById(R.id.list);
        lv.setOnItemClickListener(this);
        insert=(findViewById(R.id.insert));
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent=new Intent(PRO15Activity.this,update.class);
                myintent.putExtra("flag",5);
                startActivity(myintent);
            }
        });


        update=(findViewById(R.id.update));
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent=new Intent(PRO15Activity.this,update.class);
                myintent.putExtra("flag",1);
                startActivity(myintent);
            }
        });

        delete=(findViewById(R.id.delete));
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent=new Intent(PRO15Activity.this,update.class);
                myintent.putExtra("flag",2);
                startActivity(myintent);
            }
        });
        Cursor cur=db.query("stud",null, null, null, null, null, null);

        if(cur.getCount()>0)
        {
            String name[]=new String[cur.getCount()];
            int i=0;
            while(cur.moveToNext())
            {
                name[i++]=cur.getString(1);
            }
            ArrayAdapter aa=new ArrayAdapter(this,android.R.layout.simple_list_item_1,name);
            lv.setAdapter(aa);
        }
        else
        {
            Toast.makeText(this,"DataBase is Empty...", 1000).show();
        }
    }
    @Override
    public void onItemClick(AdapterView p, View v, int pos, long c) {
        // TODO Auto-generated method stub
        Intent myintent=new Intent(this,update.class);
        TextView iname=(TextView) v;
        myintent.putExtra("label",iname.getText().toString() );
        startActivity(myintent);
    }

}