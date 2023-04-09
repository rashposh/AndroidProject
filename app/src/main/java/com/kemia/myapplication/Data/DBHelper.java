package com.kemia.myapplication.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class DBHelper extends SQLiteOpenHelper {

    /*
     * Tạo bảng cho cơ sở dữ liệu đã được tạo ở
     * hàm khởi tạo thông qua lớp SQLiteDatabase
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //constructors :KHOI TAO DATABASE

    public DBHelper(Context context) {
        super(context, TAO_BANG_DATABASE, null, 1);
    }
    // Tên cơ sở dữ liệu
    private static final String TAO_BANG_DATABASE= "QuanLyNguoiDung";
    // Tên bảng
    public static final String TEN_BANG_DATABASE = "NguoiDung";
    // Bảng gồm 3 cột
    public static final String COT_ID = "_id";
    public static final String COT_YEUTHICH = "_yeuthich";
    public static final String COT_DAXEM = "_daxem";
    /*
     * Câu lện tạo bảng.
     * Trong đó: “integer primary key autoincrement”
     * là khóa chính tự tăng và có kiểu dữ liệu là int;
     * “text not null” là không được để trống và kiểu
     * dữ liệu là String.
     */
    private static final String DATABASE = ""
            + "create table " + TEN_BANG_DATABASE + " ( "
            + COT_ID + " integer primary key autoincrement ,"
            + COT_YEUTHICH + " text not null, "
            + COT_DAXEM + " text not null );";

    /*
     * Tạo bảng cho cơ sở dữ liệu đã được tạo ở
     * hàm khởi tạo thông qua lớp SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE);
        //Tao bang
    }
}
