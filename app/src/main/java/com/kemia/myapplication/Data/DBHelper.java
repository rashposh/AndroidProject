package com.kemia.myapplication.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import com.kemia.myapplication.Data.LSEntry;
import com.kemia.myapplication.DataYT.DBHelperyt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LMAO.db";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LSEntry.TABLE_NAME + " (" +
                    LSEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    LSEntry.COLUMN_NAME_TITLE + " TEXT," +
                    LSEntry.COLUMN_NAME_DUONG_DAN + " TEXT," +
                    LSEntry.COLUMN_NAME_DC_ANH + " TEXT," +
                    LSEntry.COLUMN_NAME_MO_TA + " TEXT," +
                    LSEntry.COLUMN_NAME_IMG + " TEXT," +
                    LSEntry.COLUMN_NAME_TG_NHAN + " DATETIME)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LSEntry.TABLE_NAME;

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBHelperyt.SQL_DELETE_ENTRIES);
        db.execSQL(DBHelper.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}