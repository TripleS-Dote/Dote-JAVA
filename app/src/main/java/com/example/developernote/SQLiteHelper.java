package com.example.developernote;

import static android.os.Build.ID;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SQLiteHelper extends SQLiteOpenHelper {
    Connection con;

    private static final String DATABASE_NAME = "loginTest.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS login");

        sqLiteDatabase.execSQL("create table login (Member_ID integer primary key autoincrement, Name text, ID text, Password text);");

        sqLiteDatabase.execSQL("INSERT INTO login VALUES (1,'Kim','admin','1234');");
        sqLiteDatabase.execSQL("INSERT INTO login VALUES (2,'Lee','admin1','12345');");
        sqLiteDatabase.execSQL("INSERT INTO login VALUES (3,'Park','admin2','123456');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<String> getMemberNames (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Name FROM login",null);
        ArrayList<String> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            result.add(cursor.getString(0));
        }
        cursor.close();
        return result;
    }

    public String findID(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ID FROM login WHERE ID = '" + id + "';", null);
        String result = "";
        if(cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        cursor.close();
        return result;
    }

    public String findPW(String id) {
        String result = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Password FROM login WHERE ID = '" + id + "';", null);
        if(cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        cursor.close();
        return result;
    }
}