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

public class MenuStudent extends AppCompatActivity {

    dbhelper enrollment;
    Button searchb, updateb;
    String s;
    public MenuStudent()
    {
        Intent i = getIntent();
        if(i!=null)
            s=i.getStringExtra("id_from_login");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        enrollment = new dbhelper(this);
        searchb=(Button)findViewById(R.id.ssearchbutton);
        updateb=(Button)findViewById(R.id.supdatebutton);

        gotosearch1();
        gotoupdate1();

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
                        Intent update = new Intent(v.getContext(),UpdateActivity.class);
                        update.putExtra("id_from_menu",s);
                        startActivity(update);
                    }
                }
        );
    }


}
