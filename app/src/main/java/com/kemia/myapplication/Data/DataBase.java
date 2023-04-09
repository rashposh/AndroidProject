package com.kemia.myapplication.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBase {
    /*
     * Lớp “SQLiteDatabase” dùng để hỗ trợ tương
     * tác với cơ sở dữ liệu.
     */
    SQLiteDatabase database;
    /*
     * Còn lớp “DBHelper” là lớp chúng
     * ta vừa tạo, dùng để tạo Cơ sở dữ liệu và bảng.
     */
    DBHelper helper;

    //constructor
    public DataBase(Context context) {
        helper = new DBHelper(context);
        /*
         * Phương thức “getWritableDatabase()” dùng
         * để tạo hoặc mở cơ sở dữ liệu để đọc và
         * ghi vào cơ sở dữ liệu.
         */
        database = helper.getWritableDatabase();
    }


    public Cursor layDuLieuYeuThich() {
        // Biến cot là khai báo danh sách các cột cần lấy.
        String[] cot = {DBHelper.COT_ID,
                DBHelper.COT_YEUTHICH,
                };
        /*
         * Cursor như là 1 bảng cơ sở dữ liệu được trả ra
         * sau khi truy vấn trong cơ sở dữ liệu.
         */
        Cursor cursor = null;
        /*
         * Dùng lớp “SQLiteDatabase” truy xuất đến phương
         * thức “query” để lấy dữ liệu trong cơ sở dữ liệu ra.
         * Ở trong phương thức này là yêu cầu lấy tất cả nên
         * chỉ cần truyền vào các tham số như: tên bảng, các
         * cột cần lấy (cot) và sắp xếp nếu cần.
         */
        cursor = database.query(DBHelper.
                        TEN_BANG_DATABASE, cot, null, null, null, null,
                DBHelper.COT_ID + " DESC");
        return cursor;
    }
    public Cursor layDuLieuDaXem() {
        String[] cot = {DBHelper.COT_ID,
                DBHelper.COT_DAXEM};
              Cursor cursor = null;
        cursor = database.query(DBHelper.
                        TEN_BANG_DATABASE, cot, null, null, null, null,
                DBHelper.COT_ID + " DESC");
        return cursor;
    }

    //them du lieu
    public long themBaiBaoYeuThich(Nguoidung nguoidung) {
        /*
         * ContentValues là đối tượng lưu trữ dữ liệu, và
         * SQLiteDatabase sẽ nhận dữ liệu thông qua đối tượng
         * này để thực hiện các câu lệnh truy vấn.
         */
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_YEUTHICH,
                nguoidung.getYeuthich());

        /*
         * Thêm vào cơ sở dữ liệu cần 2 đối số chính là
         * Tên Bảng và dữ liệu cần thêm.
         */
        return database.insert(DBHelper.
                TEN_BANG_DATABASE, null, values);
    }

    //xoa du lieu
    public long xoaBaiBaoYeuThich(Nguoidung nguoidung) {
        /*
         * Tương tự cho xóa một dòng dữ liệu cần
         * 3 đối số là tên bảng, câu điều kiện và
         * cuối cùng là đối số cho câu điều kiện
         * nhưng có thể gộp 2 đối số làm 1 như ở
         * dưới.
         */
        return database.delete(DBHelper.TEN_BANG_DATABASE,
                DBHelper.COT_YEUTHICH + " = " + "'" + nguoidung.getYeuthich() + "'", null);
    }


}

