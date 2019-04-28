package com.example.jainil.jainil2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    databasehelper helper=new databasehelper(this);
    String namestring;
    String usernamestring;
    String emailstring;
    String passstring;
    String confirmstring;
    EditText name;
    EditText username;
    EditText email;
    EditText pass;
    EditText confirmpass;
    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=(EditText)findViewById(R.id.editText7);
        username=(EditText)findViewById(R.id.editText6);
        email=(EditText)findViewById(R.id.editText5);
        pass=(EditText)findViewById(R.id.editText4);
        confirmpass=(EditText)findViewById(R.id.editText3);
        b=(Button)findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 namestring=name.getText().toString();
                 usernamestring=username.getText().toString();
                 emailstring=email.getText().toString();
                 passstring=pass.getText().toString();
                 confirmstring=confirmpass.getText().toString();
                 if(!passstring.equals(confirmstring))
                 {
                     Toast.makeText(signup.this, "Password doesnot matched", Toast.LENGTH_SHORT).show();
                 }
                 else
                 {
                     contact c=new contact();
                     c.setEmail(emailstring);
                     c.setPass(passstring);
                     c.setName(namestring);
                     c.setUname(usernamestring);
                     helper.insertcontact(c);
                     Toast.makeText(signup.this,"Data Inserted",Toast.LENGTH_LONG).show();
                 }
            }
        });



    }
}
