package com.example.jainilpatel.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    String s;
    ListView list;
    ArrayList<String> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent=getIntent();
        s=intent.getStringExtra("parcel_data");

        list=(ListView)findViewById(R.id.list);
        arr=new ArrayList<String>();
        for(int i=1;i<=Integer.parseInt(s);i++)
        {
            arr.add("ITEM:"+i);
        }

        final ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arr);

        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s="ITEM CLICKED"+(position+1);
                Toast.makeText(Main3Activity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void click(View v)
    {
        Intent i=new Intent(this,Main4Activity.class);

         startActivity(i);
    }
}
