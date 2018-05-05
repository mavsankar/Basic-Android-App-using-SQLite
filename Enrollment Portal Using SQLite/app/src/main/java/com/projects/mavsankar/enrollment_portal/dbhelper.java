package com.projects.mavsankar.enrollment_portal;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mavsa on 22-04-2018.
 */

public class dbhelper extends SQLiteOpenHelper {
    public static  final  String DBname= "ENROLLMENT.db";
    public static  final  String table1= "STUDENT";
    public static  final  String ROLL= "ROLL";
    public static  final  String SEMESTER= "SEMESTER";
    public static  final  String GENDER= "GENDER";
    public static  final  String FNAME= "FNAME";
    public static  final  String LNAME= "LNAME";
    public static  final  String DOB= "DOB";
    public static  final  String FEES= "FEES";
    public static  final  String CGPA= "CGPA";
    public static  final  String DNO= "DNO";

    public dbhelper(Context context) {
        super(context, DBname, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+table1+"(ROLL INTEGER PRIMARY KEY AUTOINCREMENT,SEMESTER INTEGER,GENDER CHAR,FNAME TEXT,LNAME TEXT,DOB DATE,FEES TEXT,CGPA DECIMAL(10,2),DNO TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+table1);
        onCreate(db);
    }
    public boolean  insertintotable1(String SEM,String GEN,String FN,String LN,String DB,String FEE,String CG,String DNUM){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
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
    public Cursor viewalltable1(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+table1,null);
        return result;
    }
    public Cursor searchtable1(String rollno){
        SQLiteDatabase db= this.getWritableDatabase();
        String q="select * from "+table1+ " where ROLL = "+ rollno;
        Cursor result = db.rawQuery(q,null);
        return result;
    }
   public Cursor keywordsearch1(String keyword){
        SQLiteDatabase db= this.getWritableDatabase();
        String q="select * from "+table1+ " where ROLL LIKE " + "'%" + keyword + "%'" + " OR SEMESTER LIKE " + "'%" + keyword + "%'"
                + " OR FNAME LIKE " + "'%" + keyword + "%'" + " OR LNAME LIKE " + "'%" + keyword + "%'" + " OR DOB LIKE " + "'%" + keyword + "%'" ;
        Cursor result1 = db.rawQuery(q,null);
        return result1;
    }
}
