package com.example.jainil.firebase;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.content.ContentValues.TAG;

public class Artistlist extends ArrayAdapter{
    DatabaseReference databaseartist;

    private Activity context;
    private String u;
    private List<Company> artistlist;
    private String companyid;

    public Artistlist(Activity contxt,List<Company> companyList)
    {
        super(contxt,R.layout.listlayout, companyList);
        this.context=contxt;
        this.artistlist= companyList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflator=context.getLayoutInflater();
       View listViewitem=inflator.inflate(R.layout.listlayout,null,true);
        TextView location=(TextView)listViewitem.findViewById(R.id.textView20);
        final TextView name=(TextView)listViewitem.findViewById(R.id.textView16);
        TextView website=(TextView)listViewitem.findViewById(R.id.textView17);
        TextView requirements=(TextView)listViewitem.findViewById(R.id.textView18);
        final TextView date=(TextView)listViewitem.findViewById(R.id.textView15);
        TextView time=(TextView)listViewitem.findViewById(R.id.textView19);
        Button btn=(Button)listViewitem.findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseartist = FirebaseDatabase.getInstance().getReference("Enrolment");

                String id=databaseartist.push().getKey();
                companyid=name.getText().toString();
                final String username= FirebaseAuth.getInstance().getCurrentUser().getUid().toString();


                DatabaseReference mdatabase=FirebaseDatabase.getInstance().getReference().child("users").child(username).child("username");
                mdatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        u=dataSnapshot.getValue().toString();
                        enrollment e=new enrollment(u,companyid,"NULL","NULL","NULL","NULL","NULL","NULL");
                        databaseartist.child(u+companyid).setValue(e);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });


        Company company =artistlist.get(position);
        location.setText(company.getLocation());
        name.setText(company.getName());
        website.setText(company.getWebsite());
        requirements.setText(company.getRequirements());
        date.setText(company.getDate());
        time.setText(company.getTime());


        return listViewitem;

    }

    public Artistlist(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public Artistlist(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public Artistlist(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }

    public Artistlist(@NonNull Context context, int resource, int textViewResourceId, @NonNull Object[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public Artistlist(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    public Artistlist(@NonNull Context context, int resource, int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
