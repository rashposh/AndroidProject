package com.kemia.myapplication.DataYT;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.kemia.myapplication.Data.DBHelper;
import com.kemia.myapplication.Data.LSEntry;

public class DBHelperyt extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LMAO.db";

    public DBHelperyt(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + YTEntry.TABLE_NAME + " (" +
                    YTEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    YTEntry.COLUMN_NAME_TITLE + " TEXT," +
                    YTEntry.COLUMN_NAME_DUONG_DAN + " TEXT," +
                    YTEntry.COLUMN_NAME_DC_ANH + " TEXT," +
                    YTEntry.COLUMN_NAME_MO_TA + " TEXT," +
                    YTEntry.COLUMN_NAME_IMG + " TEXT," +
                    YTEntry.COLUMN_NAME_TG_NHAN + " DATETIME)";



    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + YTEntry.TABLE_NAME;

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBHelperyt.SQL_CREATE_ENTRIES);
        db.execSQL(DBHelper.SQL_CREATE_ENTRIES);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBHelperyt.SQL_DELETE_ENTRIES);
        db.execSQL(DBHelper.SQL_DELETE_ENTRIES);

        onCreate(db);
    }

}

