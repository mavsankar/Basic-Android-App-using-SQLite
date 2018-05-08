package com.projects.mavsankar.enrollment_portal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.net.Uri;
import android.widget.Toast;

public class MenuStudent extends AppCompatActivity {

    dbhelper enrollment;
    Button searchb, updateb,logoutb,advisorb;
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
        advisorb=(Button)findViewById(R.id.advisorbutton);
        getadvisor();
        gotosearch1();
        gotoupdate1();
        gotologout();
    }
    public void getadvisor()
    {
        advisorb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s=getIntent().getStringExtra("id_from_login");
                        Cursor result = enrollment.findadvisor(s);
                        if (result.getCount() == 0){
                            showmsg("Error","Nothing Found");
                            return;
                        }
                        StringBuffer buffer=new StringBuffer();
                        while(result.moveToNext())
                        {
                            buffer.append("Faculty no:"+ result.getString(0)+"\n");
                            buffer.append("Fname:"+ result.getString(3)+"\n");
                            buffer.append("Lname:"+ result.getString(4)+"\n");
                            buffer.append("Room no:"+ result.getString(1)+"\n");
                            buffer.append("Gender:"+ result.getString(2)+"\n\n");



                        }
                        showmsg("Data",buffer.toString());
                    }
                }
        );
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
    public  void showmsg(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
