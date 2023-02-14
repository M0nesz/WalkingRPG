package com.example.walkinggame;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "save.db";
    public static final String USER_TABLE_NAME = "user";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_USERNAME = "username";
    public static final String USER_COLUMN_CLASS = "player_class";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table user " +
                        "(id integer primary key, username text, player_class text)"
        );

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public boolean insertUser(String username, String player_class) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if a row with id=1 already exists
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE id=1", null);
        boolean exists = cursor.moveToFirst();
        cursor.close();

        // Insert or update the row based on whether it already exists
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("player_class", player_class);
        if (exists) {
            db.update("user", contentValues, "id=?", new String[] { "1" });
        } else {
            contentValues.put("id", 1);
            db.insert("user", null, contentValues);
        }

        return true;
    }
    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from user where id="+id+"", null );
        return res;
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USER_TABLE_NAME);
        return numRows;
    }
    public boolean updateContact (Integer id, String username, String player_class) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", username);
        contentValues.put("class", player_class);
        db.update("user", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("user",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
    @SuppressLint("Range")
    public ArrayList<String> getAllUser() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from user", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(USER_COLUMN_USERNAME)));
            res.moveToNext();
        }
        return array_list;
    }
}