package com.example.randikawann.addretireveit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context , String name , SQLiteDatabase.CursorFactory factory , int version) {
        super(context , name , factory , version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student(id integer primary key, name text, mobilenumber text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion) {

    }
}
