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

public class companynamelist extends ArrayAdapter {


    DatabaseReference databaseartist;

    private Activity context;
    private String u;
    private List<Company> companylist;
    private String companyid;

    public companynamelist(Activity context,List<Company> companyList)
    {
        super(context,R.layout.listlayout, companyList);
        this.context=context;
        this.companylist= companyList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflator=context.getLayoutInflater();
        View listViewitem=inflator.inflate(R.layout.companyname,null,true);
        final TextView name=(TextView)listViewitem.findViewById(R.id.companyname);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,Teacheroptions.class);
                i.putExtra("companyname",name.getText().toString());
                context.startActivity(i);
            }
        });


        Company company =companylist.get(position);
        name.setText(company.getName());
        return listViewitem;

    }

    public companynamelist(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public companynamelist(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public companynamelist(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }

    public companynamelist(@NonNull Context context, int resource, int textViewResourceId, @NonNull Object[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public companynamelist(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    public companynamelist(@NonNull Context context, int resource, int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
