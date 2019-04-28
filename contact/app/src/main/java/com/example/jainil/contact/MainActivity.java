package com.example.jainil.contact;


import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.PhoneLookup;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class MainActivity extends Activity {
    ListView lvContacts;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContacts = (ListView) findViewById(R.id.lvContacts);


        Cursor c = managedQuery(Uri.parse("content://contacts/phones"), null, null, null, null);
        String names[] = new String[ c.getCount() ];

        int i=0;
        while (c.moveToNext())
            names[i++] = c.getString(c.getColumnIndex(PhoneLookup.DISPLAY_NAME)) + "\n" + c.getString(c.getColumnIndex(PhoneLookup.NUMBER));


        ArrayAdapter<String> adpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        lvContacts.setAdapter(adpt);
    }
}
