package com.kemia.myapplication.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.FileUtils;

import com.kemia.myapplication.Data.DBContract.LSEntry;
import com.kemia.myapplication.Fetch.GoogleNews;
import com.kemia.myapplication.Fetch.GoogleNewsItem;
import com.kemia.myapplication.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class Database {


    public Database() {

    }

    public void dropTable (Context context) {
        DBHelper helper = new DBHelper(context);
        var database = helper.getWritableDatabase();
        helper.onUpgrade(database, 1, 1);
    }



    public void addNewsItem(GoogleNewsItem item, Context context) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        var currentTime = Calendar.getInstance().getTime();
        var tgNhan = dateFormat.format(currentTime);
        addData(context, item.getTitle(),item.getImgUrl(), item.getLink(),item.getDescription(), tgNhan, item.getImgBitMap());
    }

    public void addData(Context context, String TITLE, String DC_ANH, String DUONG_DAN, String MO_TA, String TG_NHAN, Bitmap IMG) {

        DBHelper helper = new DBHelper(context);
        var database = helper.getWritableDatabase();

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
        values.put(LSEntry.COLUMN_NAME_TITLE, TITLE);
        values.put(LSEntry.COLUMN_NAME_DC_ANH, DC_ANH);
        values.put(LSEntry.COLUMN_NAME_DUONG_DAN, DUONG_DAN);
        values.put(LSEntry.COLUMN_NAME_MO_TA, MO_TA);
        values.put(LSEntry.COLUMN_NAME_TG_NHAN, TG_NHAN);
        values.put(LSEntry.COLUMN_NAME_IMG, file.getAbsolutePath());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = database.insert(LSEntry.TABLE_NAME, null, values);
    }


    public GoogleNews readFromDatabase(Context context) {

        DBHelper helper = new DBHelper(context);
        var database = helper.getReadableDatabase();

        String[] projection = {
                LSEntry.COLUMN_NAME_TITLE,
                LSEntry.COLUMN_NAME_DC_ANH,
                LSEntry.COLUMN_NAME_DUONG_DAN,
                LSEntry.COLUMN_NAME_MO_TA,
                LSEntry.COLUMN_NAME_TG_NHAN,
                LSEntry.COLUMN_NAME_IMG
        };

// Filter results WHERE "title" = 'My Title'
        String selection = LSEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "%" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                LSEntry.COLUMN_NAME_TG_NHAN + " DESC";

        Cursor cursor = database.query(
                LSEntry.TABLE_NAME,   // The table to query
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
                    cursor.getColumnIndexOrThrow(LSEntry.COLUMN_NAME_TITLE));
            String itemLink = cursor.getString(
                    cursor.getColumnIndexOrThrow(LSEntry.COLUMN_NAME_DUONG_DAN));
            String itemDescription = cursor.getString(
                    cursor.getColumnIndexOrThrow(LSEntry.COLUMN_NAME_MO_TA));
            String itemImgUrl = cursor.getString(
                    cursor.getColumnIndexOrThrow(LSEntry.COLUMN_NAME_DC_ANH));
            String imgLoc = cursor.getString(
                    cursor.getColumnIndexOrThrow(LSEntry.COLUMN_NAME_IMG));

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


}

