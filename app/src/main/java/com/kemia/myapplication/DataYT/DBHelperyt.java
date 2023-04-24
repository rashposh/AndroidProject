package com.kemia.myapplication.DataYT;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.kemia.myapplication.Data.DBContract;

public class DBHelperyt extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LMAO.db";

    public DBHelperyt(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContractyt.YTEntry.TABLE_NAME + " (" +
                    DBContractyt.YTEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContractyt.YTEntry.COLUMN_NAME_TITLE + " TEXT," +
                    DBContractyt.YTEntry.COLUMN_NAME_DUONG_DAN + " TEXT," +
                    DBContractyt.YTEntry.COLUMN_NAME_DC_ANH + " TEXT," +
                    DBContractyt.YTEntry.COLUMN_NAME_MO_TA + " TEXT," +
                    DBContractyt.YTEntry.COLUMN_NAME_IMG + " TEXT," +
                    DBContractyt.YTEntry.COLUMN_NHAN_BUTTON + " DATETIME)";



    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBContractyt.YTEntry.TABLE_NAME;

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

