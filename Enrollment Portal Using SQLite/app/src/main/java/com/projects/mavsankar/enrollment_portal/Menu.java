package com.projects.mavsankar.enrollment_portal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Menu extends InsertActivity {
    dbhelper enrollment;
    Button searchb, updateb, deleteb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        enrollment = new dbhelper(this);
        searchb=(Button)findViewById(R.id.searchbutton);
        updateb=(Button)findViewById(R.id.updatebutton);
        deleteb=(Button)findViewById(R.id.deletebutton);
        gotosearch1();
        gotoupdate1();
        gotodelete1();
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
