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

public class RegisterActivity extends AppCompatActivity {
    dbhelper enrollment;
    EditText editsem,editgen,editfn,editln,editdob,editfees,editcgpa,editdno,editroll,editsetpass;
    Button insertbutton;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        enrollment = new dbhelper(this);
        editsem=(EditText)findViewById(R.id.editText2r);
        editgen=(EditText)findViewById(R.id.editText3r);
        editfn=(EditText)findViewById(R.id.editText4r);
        editln=(EditText)findViewById(R.id.editText5r);
        editdob=(EditText)findViewById(R.id.editText6r);
        editfees=(EditText)findViewById(R.id.editText7r);
        editcgpa=(EditText)findViewById(R.id.editText8r);
        editdno=(EditText)findViewById(R.id.editText9r);
        editroll=(EditText)findViewById(R.id.editTextr);
        editsetpass=(EditText)findViewById(R.id.editText10r);

        insertbutton=(Button)findViewById(R.id.buttoninsr);
        registernew();

    }
    public void registernew()
    {
        insertbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isinserted = enrollment.insertintotable1(editroll.getText().toString(),editsem.getText().toString(),
                                editgen.getText().toString(),
                                editfn.getText().toString(),
                                editln.getText().toString(),
                                editdob.getText().toString(),
                                editfees.getText().toString(),
                                editcgpa.getText().toString(),
                                editdno.getText().toString() );
                        if(isinserted==true)
                            Toast.makeText(RegisterActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(RegisterActivity.this,"Data not Inserted",Toast.LENGTH_SHORT).show();
                        getpassword();

                    }
                }

        );

    }

    public  void getpassword()
    {
        boolean isinserted = enrollment.register(editroll.getText().toString(),editsetpass.getText().toString() );
        if(isinserted==true)
            Toast.makeText(RegisterActivity.this,"Successfull Registered",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(RegisterActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
    }
}

