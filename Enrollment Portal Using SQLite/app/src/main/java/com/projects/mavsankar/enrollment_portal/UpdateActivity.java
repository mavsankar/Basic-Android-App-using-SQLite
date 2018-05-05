package com.projects.mavsankar.enrollment_portal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends Menu {

    dbhelper enrollment;
    EditText editsem,editgen,editfn,editln,editdob,editfees,editcgpa,editdno,rollno;
    Button updatebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        enrollment = new dbhelper(this);
        editsem=(EditText)findViewById(R.id.update_sem);
        editgen=(EditText)findViewById(R.id.update_gen);
        editfn=(EditText)findViewById(R.id.update_fn);
        editln=(EditText)findViewById(R.id.update_ln);
        editdob=(EditText)findViewById(R.id.update_dob);
        editfees=(EditText)findViewById(R.id.update_fees);
        editcgpa=(EditText)findViewById(R.id.update_cgpa);
        editdno=(EditText)findViewById(R.id.update_dept);
        rollno=(EditText)findViewById(R.id.update_rollno);
        updatebutton=(Button)findViewById(R.id.updatebutton);
        updatestudent();
    }
    public void updatestudent()
    {
        updatebutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String res = enrollment.updatetable(rollno.getText().toString(),
                                editfn.getText().toString(),
                                editln.getText().toString(),
                                editsem.getText().toString(),
                                editdob.getText().toString(),
                                editgen.getText().toString(),
                                editdno.getText().toString(),
                                editcgpa.getText().toString(),
                                editfees.getText().toString()
                        );
                        Toast.makeText(UpdateActivity.this,res,Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
