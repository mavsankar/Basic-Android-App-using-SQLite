package com.projects.mavsankar.enrollment_portal;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends InsertActivity  {

    dbhelper enrollment;
    EditText getroll;
    Button searchbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        enrollment = new dbhelper(this);
        getroll=(EditText)findViewById(R.id.rollsearch);
        searchbutton=(Button)findViewById(R.id.searchbutton);
        searchtable1();
    }

    public void searchtable1()
    {

        searchbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result = enrollment.searchtable1(getroll.getText().toString());
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
                            buffer.append("FEES:"+ result.getString(6)+"\n");

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
