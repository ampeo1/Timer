package com.example.timer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class Database {
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    Cursor userCursor;

    public Database(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.openDatabase();
        try {
            dbHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
    }

    public ArrayList<Sequence> getSequence(){
        ArrayList<Sequence> sequences = new ArrayList<Sequence>();
        try{
            userCursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE, null);
        }
        catch (Exception ex){
            Log.d("getSequence", ex.getMessage());
        }
        if(userCursor.moveToFirst()){
            do{
                sequences.add(new Sequence(userCursor.getInt(0),
                        userCursor.getString(1), userCursor.getString(2)));
            }
            while(userCursor.moveToNext());
        }
        userCursor.close();
        db.close();
        return sequences;
    }

    public void addSequence(Sequence sequence){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, sequence.getName());
        cv.put(DatabaseHelper.COLUMN_COLOR, sequence.getColor());
        db.insert(DatabaseHelper.TABLE, null, cv);
    }
}
