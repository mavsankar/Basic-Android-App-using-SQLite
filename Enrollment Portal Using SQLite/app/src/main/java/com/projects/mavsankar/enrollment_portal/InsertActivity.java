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

public class InsertActivity extends Menu {

    dbhelper enrollment;
    EditText editsem,editgen,editfn,editln,editdob,editfees,editcgpa,editdno,editroll;
    Button insertbutton,viewallbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        enrollment = new dbhelper(this);
        editsem=(EditText)findViewById(R.id.editText2);
        editgen=(EditText)findViewById(R.id.editText3);
        editfn=(EditText)findViewById(R.id.editText4);
        editln=(EditText)findViewById(R.id.editText5);
        editdob=(EditText)findViewById(R.id.editText6);
        editfees=(EditText)findViewById(R.id.editText7);
        editcgpa=(EditText)findViewById(R.id.editText8);
        editdno=(EditText)findViewById(R.id.editText9);
        editroll=(EditText)findViewById(R.id.editText);
        insertbutton=(Button)findViewById(R.id.buttonins);
        viewallbutton=(Button)findViewById(R.id.buttonviewall);

        insertintostudent();
        viewallstudent();
    }

    public void insertintostudent()
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
                            Toast.makeText(InsertActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(InsertActivity.this,"Data not Inserted",Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }
    public void viewallstudent()
    {
        viewallbutton.setOnClickListener(

                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Cursor result = enrollment.viewalltable1();
                                if (result.getCount() == 0){
                                    showmsg("Error","Nothing Found");
                                    return;
                                }
                                StringBuffer buffer=new StringBuffer();
                                while(result.moveToNext())
                                {
                                    buffer.append("ROLL:"+ result.getString(0)+"\n");
                                    buffer.append("FIRST NAME:"+ result.getString(3)+"\n");
                                    buffer.append("LAST NAME:"+ result.getString(4)+"\n");
                                    buffer.append("SEMESTER:"+ result.getString(1)+"\n");
                                    buffer.append("DATE OF BIRTH:"+ result.getString(5)+"\n");
                                    buffer.append("GENDER:"+ result.getString(2)+"\n");
                                    buffer.append("DEPARTMENT:"+ result.getString(8)+"\n");
                                    buffer.append("CGPA:"+ result.getString(7)+"\n");
                                    buffer.append("FEES:"+ result.getString(6)+"\n\n");

                                }
                                showmsg("Data",buffer.toString());

                            }
                        }

        );
    }

    public  void showmsg(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}

