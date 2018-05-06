package com.projects.mavsankar.enrollment_portal;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by mavsa on 22-04-2018.
 */

public class dbhelper extends SQLiteOpenHelper {
    public static  final  String DBname= "ENROLLMENT.db";
    public static  final  String table1= "STUDENT";
    public static final String tablep="PasswordTable";
    public static  final  String ROLL= "ROLL";
    public  static final String ID="ID";
    public static  final  String SEMESTER= "SEMESTER";
    public static  final  String GENDER= "GENDER";
    public static  final  String FNAME= "FNAME";
    public static  final  String LNAME= "LNAME";
    public static  final  String DOB= "DOB";
    public static  final  String FEES= "FEES";
    public static  final  String CGPA= "CGPA";
    public static  final  String DNO= "DNO";
    public static  final  String PASSWORD= "PASSWORD";

    public dbhelper(Context context) {
        super(context, DBname, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+table1+"(ROLL VARCHAR(10) PRIMARY KEY NOT NULL,SEMESTER INTEGER ,GENDER CHAR ,FNAME TEXT ,LNAME TEXT  ,DOB DATE  ,FEES TEXT  ,CGPA DECIMAL(10,2)  ,DNO TEXT  )");
        db.execSQL("create table "+tablep+ "(ID VARCHAR(10) PRIMARY KEY NOT NULL ,PASSWORD TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+table1);
        db.execSQL("DROP TABLE IF EXISTS "+tablep);

        onCreate(db);
    }
    public boolean  insertintotable1(String RNO,String SEM,String GEN,String FN,String LN,String DB,String FEE,String CG,String DNUM){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ROLL,RNO);

        contentValues.put(SEMESTER,SEM);
        contentValues.put(GENDER,GEN);
        contentValues.put(FNAME,FN);
        contentValues.put(LNAME,LN);
        contentValues.put(DOB,DB);
        contentValues.put(FEES,FEE);
        contentValues.put(CGPA,CG);
        contentValues.put(DNO,DNUM);
        long result = db.insert(table1,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;



    }
    public boolean register(String idno, String pass){

        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues1=new ContentValues();
        contentValues1.put(ID,idno);
        contentValues1.put(PASSWORD,pass);

        long result = db.insert(tablep,null,contentValues1);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor viewalltable1(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+table1,null);
        return result;
    }

    public Cursor searchtable1(String rollno){
        SQLiteDatabase db= this.getWritableDatabase();
        String q="select * from "+table1+ " where ROLL = "+ "'" +rollno+"'";
        Cursor result = db.rawQuery(q,null);
        return result;
    }

    public Cursor signin1(String id,String Pass){
        SQLiteDatabase db= this.getWritableDatabase();
        String q="select * from "+tablep+ " where ID = "+ "'"+id+"'" + " AND PASSWORD= " + "'" +Pass+"'";
        Cursor result = db.rawQuery(q,null);
        return result;
    }


    public String updatetable(String rollno, String fn, String ln, String sem, String dob, String gen, String dept, String cgpa, String fee){
        if (rollno.length() == 0)
            return "Roll No. can't be empty!";
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "update "+table1+" set ";
        int cnt = 0;
        if (fn.length() != 0)
        {
            cnt++;
            q = q + FNAME + " = " + "'" + fn + "'";
        }
        if (ln.length() != 0)
        {
            if (cnt != 0)
            {
                q = q + ", " + LNAME + "=" + "'" + ln + "'";
            }
            else
            {
                q = q + LNAME + "=" + "'" + ln + "'";
            }
            cnt++;
        }
        if (sem.length() != 0)
        {
            if (cnt != 0)
            {
                q = q + ", " + SEMESTER + "=" + sem;
            }
            else
            {
                q = q + SEMESTER + "=" + sem;
            }
            cnt++;
        }
        if (dob.length() != 0)
        {
            if (cnt != 0)
            {
                q = q + ", " + DOB + "=" + "'" + dob + "'";
            }
            else
            {
                q = q + DOB + "=" + "'" + dob + "'";
            }
            cnt++;
        }
        if (gen.length() != 0)
        {
            if (cnt != 0)
            {
                q = q + ", " + GENDER + "=" + "'" + gen + "'";
            }
            else
            {
                q = q + GENDER + "=" + "'" + gen + "'";
            }
            cnt++;
        }
        if (dept.length() != 0)
        {
            if (cnt != 0)
            {
                q = q + ", " + DNO + "=" + "'" + dept + "'";
            }
            else
            {
                q = q + DNO + "=" + "'" + dept + "'";
            }
            cnt++;
        }
        if (cgpa.length() != 0)
        {
            if (cnt != 0)
            {
                q = q + ", " + CGPA + "=" + cgpa;
            }
            else
            {
                q = q + CGPA + "=" + cgpa;
            }
            cnt++;
        }
        if (fee.length() != 0)
        {
            if (cnt != 0)
            {
                q = q + ", " + FEES + "=" + "'" + fee + "'";
            }
            else
            {
                q = q + FEES + "=" + "'" + fee + "'";
            }
            cnt++;
        }
        q = q + " WHERE " + ROLL + " = " + "'"+rollno+"'";
        if (cnt == 0)
            return "No updates?";
        Cursor result = db.rawQuery(q,null);
        result.moveToFirst();
        result.close();
        return "Updated";
    }

    public String deleterecord(String rollno) {
        if (rollno.length() == 0)
            return "Roll No. can't be empty!";
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "delete from STUDENT where ROLL = " + "'" +rollno+"'";
        Cursor res = db.rawQuery(q, null);
        res.moveToFirst();
        res.close();
        return "Deleted!";
    }

    public Cursor keywordsearch1(String keyword){
        SQLiteDatabase db= this.getWritableDatabase();
        String q="select * from "+table1+ " where ROLL LIKE " + "'%" + keyword + "%'" + " OR SEMESTER LIKE " + "'%" + keyword + "%'"
                + " OR FNAME LIKE " + "'%" + keyword + "%'" + " OR LNAME LIKE " + "'%" + keyword + "%'" + " OR DOB LIKE " + "'%" + keyword + "%'" ;
        Cursor result1 = db.rawQuery(q,null);
        return result1;
    }

    public Cursor feesnotpaid(){
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "select ROLL from "+table1+" where Lower(FEES) LIKE '%due%'";
        Cursor res = db.rawQuery(q, null);
        return res;
    }
}
