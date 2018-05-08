package com.projects.mavsankar.enrollment_portal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertFaculty extends Menu {
    dbhelper enrollment;
    Button facultyb;
    EditText ffname, flname, froom, fgen, fadvices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_faculty);
        enrollment = new dbhelper(this);
        facultyb=(Button)findViewById(R.id.facultyb);
        ffname=(EditText)findViewById(R.id.faculty_fname);
        flname=(EditText)findViewById(R.id.faculty_lname);
        froom=(EditText)findViewById(R.id.faculty_room);
        fgen=(EditText)findViewById(R.id.faculty_gender);
        fadvices=(EditText)findViewById(R.id.faculty_advices);
        facultyadd();
    }
    public void facultyadd()
    {
        facultyb.setOnClickListener(
                new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {
                        boolean isins = enrollment.insertintotablef(froom.getText().toString(), fgen.getText().toString(), ffname.getText().toString(), flname.getText().toString(), fadvices.getText().toString());
                        if (isins)
                            Toast.makeText(InsertFaculty.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(InsertFaculty.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
