package com.projects.mavsankar.enrollment_portal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Menu extends AppCompatActivity {
    dbhelper enrollment;
    Button searchb, updateb, deleteb,enroll;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        enrollment = new dbhelper(this);
        searchb=(Button)findViewById(R.id.searchbutton);
        updateb=(Button)findViewById(R.id.updatebutton);
        deleteb=(Button)findViewById(R.id.deletebutton);
        enroll=(Button)findViewById(R.id.enroll);
        gotoenroll();
        gotosearch1();
        gotoupdate1();
        gotodelete1();
    }
    public void gotoenroll()
    {
        enroll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent search = new Intent(v.getContext(),InsertActivity.class);
                        startActivity(search);
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

                        Intent update = new Intent(v.getContext(),UpdateActivity.class);
                        update.putExtra("id_from_menu",s);

                        startActivity(update);
                    }
                }
        );
    }
    public void gotodelete1()
    {
        deleteb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent X = new Intent(v.getContext(),Delete.class);
                        startActivity(X);
                    }
                }
        );
    }
}
