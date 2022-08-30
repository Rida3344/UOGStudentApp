package com.example.semesterfreeze;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BDHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Application.db";
    private static final String TABLE_FREEZE_FORM = "semesterfreeze";
    private static final String STUDENT_id = "uid";
    private static final String application_id = "id";
    private static final String reason= "Reason";
    private static final String cs = "currentSemester";
    private static final String fs = "freezesemester";

    public BDHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SF_TABLE = "CREATE TABLE " + TABLE_FREEZE_FORM + "("
                + STUDENT_id + " TEXT,"
                +application_id + " INGETER,"
                + cs + " TEXT,"
                + fs + " TEXT,"
                + reason + " TEXT,"
        + " PRIMARY KEY(id));";
        db.execSQL(CREATE_SF_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean insert(SemesterFreeze sf){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue1= new ContentValues();
        contentValue1.put(application_id, sf.getId());
        contentValue1.put(STUDENT_id, SignIn.lc.getUname());
        contentValue1.put(cs, sf.getCurrentsamester());
        contentValue1.put(fs, sf.getSamesterfreeze());
        contentValue1.put(reason, sf.getReason());
        long result = db.insert(TABLE_FREEZE_FORM, null, contentValue1);
        if(result == -1)
            return false;
        else
            return true;
    }

    public List<SemesterFreeze> readallrecords(){
        List<SemesterFreeze> sf = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM semesterfreeze WHERE "+ STUDENT_id + " = ?", new String[] {SignIn.lc.getUname()});
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            do{
                sf.add(new SemesterFreeze(cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while(cursor.moveToNext());
        }

        return sf;
    }

    public boolean checkFormId(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_FREEZE_FORM+" WHERE "+ application_id + " = "+id, new String[] {});
        if(cursor.getCount()>0){
            return false;
        }
        else
            return true;
    }

    public boolean deleterecord(int pos){
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_FREEZE_FORM, "id = "+MainActivity.list.get(pos).getId(), new String[] {});
        db.close();
        return true;
    }

}