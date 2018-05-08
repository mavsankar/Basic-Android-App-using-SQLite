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

public class Delete extends AppCompatActivity {

    dbhelper enrollment;
    EditText rollno;
    Button deletebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        enrollment = new dbhelper(this);
        rollno=(EditText)findViewById(R.id.delete_rollno);
        deletebutton=(Button)findViewById(R.id.deletebutton);
        deletestudent();
    }

    public void deletestudent(){
        deletebutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String res = enrollment.deleterecord(rollno.getText().toString());
                        Toast.makeText(Delete.this, res, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
