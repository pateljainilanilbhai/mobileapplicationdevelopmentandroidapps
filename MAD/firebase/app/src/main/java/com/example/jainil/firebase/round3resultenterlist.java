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
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.List;

public class round3resultenterlist extends ArrayAdapter {


    DatabaseReference databaseartist;

    private Activity context;
    private String u;
    private List<enrollment> userlist;
    private String companyid;


    public round3resultenterlist(Activity context, List<enrollment> companyList)
    {
        super(context,R.layout.round1resultenterlist, companyList);
        this.context=context;
        this.userlist= companyList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflator=context.getLayoutInflater();
        View listViewitem=inflator.inflate(R.layout.round1resultenterlist,null,true);
        final TextView username=(TextView)listViewitem.findViewById(R.id.textView12);
        final CheckBox c=(CheckBox)listViewitem.findViewById(R.id.checkBox3);

        final enrollment user =userlist.get(position);
        username.setText(user.getUsername());

        if(user.round3result.equals("Pass"))
        {
            c.setChecked(true);
        }

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(c.isChecked())
                {
                    user.round3result="Pass";
                    DatabaseReference f= FirebaseDatabase.getInstance().getReference("Enrolment");
                    f.child(user.getUsername()+user.getCompanyid()).setValue(user);

                }
                else
                {
                    user.round3result="NULL";
                    DatabaseReference f=FirebaseDatabase.getInstance().getReference("Enrolment");
                    f.child(user.getUsername()+user.getCompanyid()).setValue(user);

                }

            }
        });

        return listViewitem;

    }

    public round3resultenterlist(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public round3resultenterlist(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public round3resultenterlist(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }

    public round3resultenterlist(@NonNull Context context, int resource, int textViewResourceId, @NonNull Object[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public round3resultenterlist(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    public round3resultenterlist(@NonNull Context context, int resource, int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
    }

}
