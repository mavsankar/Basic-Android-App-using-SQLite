package com.projects.mavsankar.enrollment_portal;

import android.database.Cursor;
import android.os.Bundle;
import android.content.Intent;
import android.sax.StartElementListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button registerbutton,login;
    dbhelper enrollment;
    EditText editid,editpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        enrollment = new dbhelper(this);
        registerbutton=(Button)findViewById(R.id.registerb);
        login=(Button)findViewById(R.id.buttonsignin);
        editid=(EditText)findViewById(R.id.idnum);
        editpass=(EditText)findViewById(R.id.passwordnum) ;
        signin();
        registeration1();

    }
    public void registeration1()
    {
        registerbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      //  Toast.makeText(Login.this, " Id should be 9 characters ", Toast.LENGTH_SHORT).show();

                      Intent reg = new Intent(view.getContext(), RegisterActivity.class);
                      startActivity(reg);
                    }
                }
        );
    }
    public void signin()
    {
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(editid.getText().toString().length()<9 && !editid.getText().toString().equals("ADMIN") )
                        {
                            Toast.makeText(Login.this, " Id should be 9 characters ", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Cursor result = enrollment.signin1(editid.getText().toString(),
                                editpass.getText().toString() );
                        if(result.getCount()==0) {
                            Toast.makeText(Login.this, " Wrong Credentials ", Toast.LENGTH_SHORT).show();
                        return;
                        }
                            Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                            String p = editid.getText().toString();
                            if(p.equals("ADMIN")) {
                              Toast.makeText(Login.this, "You are an Admin!", Toast.LENGTH_SHORT).show();

                             Intent menu1=new Intent(view.getContext(),Menu.class);
                                menu1.putExtra("id_from_login",p);

                              startActivity(menu1);
                            }
                            else
                            {
                                Intent menu=new Intent(view.getContext(),MenuStudent.class);
                                menu.putExtra("id_from_login",p);

                                startActivity(menu);
                            }
                    }
                }
        );
    }
}
