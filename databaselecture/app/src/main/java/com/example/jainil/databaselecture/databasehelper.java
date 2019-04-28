package com.example.jainil.databaselecture;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class databasehelper extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "credentialpatelanil.db";
    private static final String table_name = "credentialpatelj";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_Name = "name";
    private static final String COLUMN_UName = "uname";
    private static final String COLUMN_Email = "email";
    private static final String COLUMN_Pass = "pass";
    SQLiteDatabase db;



    public databasehelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public databasehelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public databasehelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("create table "+table_name+" (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, uname TEXT NOT NULL, email TEXT NOT NULL, pass TEXT NOT NULL );");
        db.execSQL("create table if not exists credentialpatelj(id integer primary key AUTOINCREMENT,name text,uname text,email text,pass text)");

        this.db=db;


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
      String query="DROP TABLE IF EXISTS "+table_name;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insertcontact(contact c)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_Name,c.getName());
        values.put(COLUMN_Email,c.getEmail());
        values.put(COLUMN_Pass,c.getPass());
        values.put(COLUMN_UName,c.getUname());
        db.insert(table_name,null,values);


    }
}
