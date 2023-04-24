package com.kemia.myapplication.DataYT;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.kemia.myapplication.Data.LSEntry;
import com.kemia.myapplication.DataYT.DBContractyt.YTEntry;
import com.kemia.myapplication.Data.DBHelper;
import com.kemia.myapplication.Fetch.GoogleNews;
import com.kemia.myapplication.Fetch.GoogleNewsItem;
import com.kemia.myapplication.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class Databaseyt {


    public Databaseyt() {

    }

    public void dropTable (Context context) {
        DBHelper helper = new DBHelper(context);
        var database = helper.getWritableDatabase();
        helper.onUpgrade(database, 1, 1);
    }





    public boolean checkIfExist(Context context, String duongDan) {
        DBHelper helper = new DBHelper(context);
        var database = helper.getReadableDatabase();

        String query = String.format("SELECT * FROM %s WHERE %s='%s'", YTEntry.TABLE_NAME, YTEntry.COLUMN_NAME_DUONG_DAN, duongDan);
        var cur = database.rawQuery(query, null);

        return cur.moveToNext();
    }

    public void addData(Context context, String TITLE, String DC_ANH, String DUONG_DAN, String MO_TA, String TG_NHAN, Bitmap IMG) {



        if (Objects.isNull(IMG)) {
            IMG = BitmapFactory.decodeResource(context.getResources(), R.drawable.bruh);
        }
        // Create a new map of values, where column names are the keys
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        IMG.compress(Bitmap.CompressFormat.PNG, 100, bos);
        var img = bos.toByteArray();
        File directory = context.getFilesDir();
        File file = new File(directory,TG_NHAN+".png");
        if (!file.exists()) {
            try {
                file.createNewFile();

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bos.toByteArray());
                fos.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        ContentValues values = new ContentValues();
        values.put(YTEntry.COLUMN_NAME_TITLE, TITLE);
        values.put(YTEntry.COLUMN_NAME_DC_ANH, DC_ANH);
        values.put(YTEntry.COLUMN_NAME_DUONG_DAN, DUONG_DAN);
        values.put(YTEntry.COLUMN_NAME_MO_TA, MO_TA);
        values.put(YTEntry.COLUMN_NAME_IMG, file.getAbsolutePath());



        if (checkIfExist(context, DUONG_DAN)) {
            DBHelper helper = new DBHelper(context);
            var database = helper.getWritableDatabase();

            // Insert the new row, returning the primary key value of the new row
            long newRowId = database.update(YTEntry.TABLE_NAME, values, YTEntry.COLUMN_NAME_TITLE+"=?", new String[]{TITLE});

        }
        else {
            DBHelper helper = new DBHelper(context);
            var database = helper.getWritableDatabase();

            // Insert the new row, returning the primary key value of the new row
            long newRowId = database.insert(YTEntry.TABLE_NAME, null, values);

        }

    }


    public GoogleNews readFromDatabase(Context context) {

        DBHelperyt helper = new DBHelperyt(context);
        var database = helper.getReadableDatabase();

        String[] projection = {
                YTEntry.COLUMN_NAME_TITLE,
                YTEntry.COLUMN_NAME_DC_ANH,
                YTEntry.COLUMN_NAME_DUONG_DAN,
                YTEntry.COLUMN_NAME_MO_TA,

                YTEntry.COLUMN_NAME_IMG
        };

// Filter results WHERE "title" = 'My Title'
        String selection = YTEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "%" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                DBContractyt.YTEntry.COLUMN_NHAN_BUTTON + " DESC";
        Cursor cursor = database.query(
                YTEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        ArrayList<GoogleNewsItem> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            String itemTitle = cursor.getString(
                    cursor.getColumnIndexOrThrow(YTEntry.COLUMN_NAME_TITLE));
            String itemLink = cursor.getString(
                    cursor.getColumnIndexOrThrow(YTEntry.COLUMN_NAME_DUONG_DAN));
            String itemDescription = cursor.getString(
                    cursor.getColumnIndexOrThrow(YTEntry.COLUMN_NAME_MO_TA));
            String itemImgUrl = cursor.getString(
                    cursor.getColumnIndexOrThrow(YTEntry.COLUMN_NAME_DC_ANH));
            String imgLoc = cursor.getString(
                    cursor.getColumnIndexOrThrow(YTEntry.COLUMN_NAME_IMG));

            File file = new File(imgLoc);
            byte[] data = new byte[(int) file.length()];
            try {
                BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
                buf.read(data, 0, data.length);
                buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            var img = BitmapFactory.decodeByteArray(data, 0, data.length);

            GoogleNewsItem ggNewItem = new GoogleNewsItem(itemTitle, itemLink,itemDescription,itemImgUrl, img);
            items.add(ggNewItem);
        }
        cursor.close();
        return new GoogleNews(items);
    }


    public void deleteItem(Context context,GoogleNewsItem newsItem) {
        DBHelper helper = new DBHelper(context);
        var database = helper.getWritableDatabase();

        database.delete(YTEntry.TABLE_NAME, YTEntry.COLUMN_NAME_DUONG_DAN+"=?", new String[]{newsItem.getLink()});

    }
}
