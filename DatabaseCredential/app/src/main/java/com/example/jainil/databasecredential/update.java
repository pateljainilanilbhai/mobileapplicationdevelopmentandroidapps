package com.example.jainil.databasecredential;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class update extends Activity implements OnClickListener, TextWatcher, OnItemClickListener  {

    SQLiteDatabase db;
    EditText txtname,txtage,txtid;
    Button btnedit,btncancel;
    TextView lblage;
    ListView lv;
    String name[];
    int flag=-1;
    @SuppressLint("WrongConstant")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db=openOrCreateDatabase("database1",SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db.execSQL("create table if not exists stud(id integer primary key AUTOINCREMENT,name text,age integer)");

        lblage=(TextView) findViewById(R.id.textView2);
        txtname=(EditText) findViewById(R.id.txtname);
        txtage=(EditText) findViewById(R.id.txtage);
        txtid=(EditText) findViewById(R.id.txtid);
        btnedit=(Button) findViewById(R.id.btnedit);
        btncancel=(Button) findViewById(R.id.btncancel);

        lv=(ListView) findViewById(R.id.listv);
        lv.setVisibility(-1);
        txtid.setVisibility(-1);
        Intent myintent=getIntent();
        flag=myintent.getIntExtra("flag", 0);
        if(flag==0)
        {
            try
            {
                String label=myintent.getStringExtra("label");
                Cursor cur=db.query("stud",null,"name=?",new String []{label},null, null,null);
                cur.moveToFirst();
                Toast.makeText(this,cur.getString(0)+"", 1).show();
                txtid.setText(cur.getString(0)+"");
                txtname.setText(cur.getString(1));
                txtage.setText(cur.getString(2));
                btnedit.setText("Update");
                txtage.setEnabled(true);
            }
            catch(Exception e)
            {
                Toast.makeText(this,e.toString(), 1).show();
            }
        }
        else if(flag==1)
        {
            txtname.addTextChangedListener(this);
            lv.setVisibility(0);
            invisible();
            txtage.setEnabled(false);
        }
        else if(flag==2)
        {
            txtname.addTextChangedListener(this);
            lv.setVisibility(0);
            invisible();
            btnedit.setText("Delete");
        }
        else if(flag==5)
        {
            btnedit.setText("Save");
        }
        btnedit.setOnClickListener(this);
        btncancel.setOnClickListener(this);
        lv.setOnItemClickListener(this);
    }
    public void invisible()
    {
        lblage.setVisibility(-1);
        txtage.setVisibility(-1);
        btncancel.setVisibility(-1);
        btnedit.setVisibility(-1);
    }
    public void visible()
    {
        lblage.setVisibility(0);
        txtage.setVisibility(0);
        btncancel.setVisibility(0);
        btnedit.setVisibility(0);
    }
    public void clear()
    {
        txtid.setText("");
        txtname.setText("");
        txtage.setText("");
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Button action=(Button) v;
        if(action.getText().toString().equals("Edit"))
        {
            txtage.setEnabled(true);
            btnedit.setText("Update");
        }
        else if(action.getText().toString().equals("Update"))
        {
            ContentValues value=new ContentValues();
            value.put("name", txtname.getText().toString());
            value.put("age",txtage.getText().toString());
            db.update("stud",value, "id=?", new String[]{txtid.getText().toString()});
            btnedit.setText("Edit");
            Toast.makeText(this,"Update Successfully",1000).show();
            startActivity(new Intent(this,PRO15Activity.class));
        }
        else if(action.getText().toString().equals("Delete"))
        {
            db.delete("stud", "id=?",new String[]{txtid.getText().toString()});
            Toast.makeText(this, "Record Deleted...", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,PRO15Activity.class));
        }
        else if(action.getText().toString().equals("Save"))
        {
            if(txtname.getText().toString().equals(""))
            {
                Toast.makeText(this,"Please Enter Name ...", 1).show();
            }
            else if(txtage.getText().toString().equals(""))
            {
                Toast.makeText(this,"Please Enter Age ...", 1).show();
            }
            else
            {
                try
                {
                    Cursor cur=db.query("stud", null, "name=?",new String[]{txtname.getText().toString()},null, null,null);
                    if(cur.getCount()>0)
                    {
                        Toast.makeText(this,"Name Allready Exists ...",1).show();
                    }
                    else
                    {
                        ContentValues values=new ContentValues();
                        values.put("name", txtname.getText().toString());
                        values.put("age",txtage.getText().toString());
                        db.insert("stud",null, values);
                        Toast.makeText(this,"Record Saved...", 1).show();
                    }
                }
                catch(Exception e)
                {
                    Toast.makeText(this,e.toString(), 1).show();
                }
            }
        }
        else if(v.getId()==btncancel.getId())
        {
            startActivity(new Intent(this,PRO15Activity.class));
        }
    }
    public void enabled()
    {
        txtage.setEnabled(true);
    }
    public void disabled()
    {
        txtage.setEnabled(false);
    }
    @Override
    public void afterTextChanged(Editable arg0) {
        // TODO Auto-generated method stub
        Cursor cur=db.rawQuery("select *from stud where name like '"+txtname.getText().toString()+"%'",null);
        int len=cur.getCount();
        try
        {
            if(txtname.getText().toString().equals(""))
            {
                lv.setAdapter(null);
                name[0]="";
            }
            else if(len>0)
            {
                if(txtname.getText().toString().equals(""))
                {
                    lv.setAdapter(null);
                    for(int i=0; i<len; i++)
                        name[i]="";
                }
                else if(len>0)
                {
                    name=new String[len];
                    cur.moveToNext();
                    for(int i=0; i<len; i++)
                    {
                        name[i]=cur.getString(1);
                        cur.moveToNext();
                    }
                }
                ArrayAdapter<String> aa=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name);
                lv.setAdapter(aa);
            }
            else
            {
                Toast.makeText(this,"Recoerd Not Found...", 1000).show();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this,e.toString(),1000).show();
        }
    }
    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
        // TODO Auto-generated method stub
    }
    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }
    @Override
    public void onItemClick(AdapterView<?> p, View v, int pos, long c) {
        // TODO Auto-generated method stub
        TextView iname=(TextView) v;
        String sname=iname.getText().toString();

        Cursor cur=db.rawQuery("select *from stud",null);
        if(cur.getCount()>0)
        {
            while(cur.moveToNext())
            {
                if(sname.equals(cur.getString(1)))
                {
                    txtid.setText(cur.getInt(0)+"");
                    txtname.setText(cur.getString(1));
                    txtage.setText(cur.getString(2));
                    break;
                }
            }
            visible();
            lv.setVisibility(-1);
        }
    }
}