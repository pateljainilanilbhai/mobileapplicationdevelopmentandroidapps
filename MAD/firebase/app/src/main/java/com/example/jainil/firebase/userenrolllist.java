package com.example.jainil.firebase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;


import java.util.List;

public class userenrolllist extends ArrayAdapter {


    DatabaseReference databaseartist;

    private Activity context;
    private String u;
    private List<enrollment> userlist;
    private String companyid;

    public userenrolllist(Activity context, List<enrollment> companyList)
    {
        super(context,R.layout.userenrolllist, companyList);
        this.context=context;
        this.userlist= companyList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflator=context.getLayoutInflater();
        View listViewitem=inflator.inflate(R.layout.userenrolllist,null,true);
        final TextView username=(TextView)listViewitem.findViewById(R.id.textView5);

        enrollment user =userlist.get(position);
        username.setText(user.getUsername());
        return listViewitem;

    }

    public userenrolllist(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public userenrolllist(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public userenrolllist(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }

    public userenrolllist(@NonNull Context context, int resource, int textViewResourceId, @NonNull Object[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public userenrolllist(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    public userenrolllist(@NonNull Context context, int resource, int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
