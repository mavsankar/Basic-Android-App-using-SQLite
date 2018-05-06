package com.projects.mavsankar.enrollment_portal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.net.Uri;
import android.widget.Toast;

public class MenuStudent extends AppCompatActivity {

    dbhelper enrollment;
    Button searchb, updateb,logoutb;
    String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        enrollment = new dbhelper(this);
        searchb=(Button)findViewById(R.id.ssearchbutton);
        updateb=(Button)findViewById(R.id.supdatebutton);
        logoutb=(Button)findViewById(R.id.logoutbutton);
        gotosearch1();
        gotoupdate1();
        gotologout();
    }
    public void gotologout()
    {
        logoutb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
        );
    }
    public void gotosearch1()
    {
        searchb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent search = new Intent(v.getContext(),SearchActivity.class);
                        startActivity(search);
                    }
                }
        );
    }

    public void gotoupdate1()
    {
        updateb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s=getIntent().getStringExtra("id_from_login");
                    //    Toast.makeText(MenuStudent.this,"came to update "+ s, Toast.LENGTH_SHORT).show();

                        Intent update = new Intent(v.getContext(),UpdateActivity.class);
                        update.putExtra("id_from_menu",s);
                        startActivity(update);
                    }
                }
        );
    }


}
