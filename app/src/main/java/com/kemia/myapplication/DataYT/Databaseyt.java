package com.kemia.myapplication.DataYT;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
        DBHelperyt helper = new DBHelperyt(context);
        var database = helper.getWritableDatabase();
        helper.onUpgrade(database, 1, 1);
    }



    public void addNewsItem(GoogleNewsItem item, Context context) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);//tạo đối tượng để định dạng ngày thàng năm
        var currentTime = Calendar.getInstance().getTime();// lấy thời gian hiện tại
        var tgNhan = dateFormat.format(currentTime);//chỉnh sửa thời gian đó thành định dạng trên
        addData(context, item.getTitle(),item.getImgUrl(), item.getLink(),item.getDescription(), tgNhan, item.getImgBitMap());//thêm dữ liệu vào sqlite
    }

    public boolean checkIfExist(Context context, String duongDan) {// kiểm tra nó có trong cở sở dữ liệu không
        DBHelperyt helper = new DBHelperyt(context);
        var database = helper.getReadableDatabase();

        String query = String.format("SELECT * FROM %s WHERE %s='%s'", YTEntry.TABLE_NAME, YTEntry.COLUMN_NAME_DUONG_DAN, duongDan);
        var cur = database.rawQuery(query, null);

        return cur.moveToNext();
    }

    public void addData(Context context, String TITLE, String DC_ANH, String DUONG_DAN, String MO_TA, String TG_NHAN, Bitmap IMG) {

        if (Objects.isNull(IMG)) {//nếu kh có ảnh thì cho ảnh bruh vào
            IMG = BitmapFactory.decodeResource(context.getResources(), R.drawable.bruh);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        IMG.compress(Bitmap.CompressFormat.PNG, 100, bos);// biến ảnh thành 1 mảng dữ liệu
        var img = bos.toByteArray();
        File directory = context.getFilesDir();//lấy vị trí của ứng dụng
        File file = new File(directory,TG_NHAN+".png");// vị trí file chứa dữ liệu
        if (!file.exists()) {
            try {
                file.createNewFile();//taọ file dựa vào vị trí đó

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bos.toByteArray());
                fos.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        ContentValues values = new ContentValues();// đặt tên cho từng cột trong csdl
        values.put(YTEntry.COLUMN_NAME_TITLE, TITLE);
        values.put(YTEntry.COLUMN_NAME_DC_ANH, DC_ANH);
        values.put(YTEntry.COLUMN_NAME_DUONG_DAN, DUONG_DAN);
        values.put(YTEntry.COLUMN_NAME_MO_TA, MO_TA);
        values.put(YTEntry.COLUMN_NAME_TG_NHAN, TG_NHAN);
        values.put(YTEntry.COLUMN_NAME_IMG, file.getAbsolutePath());



        if (checkIfExist(context, DUONG_DAN)) {//nếu nó có trong sqlite thì đem nó lên trên
            DBHelperyt helper = new DBHelperyt(context);
            var database = helper.getWritableDatabase();

            long newRowId = database.update(YTEntry.TABLE_NAME, values, YTEntry.COLUMN_NAME_TITLE+"=?", new String[]{TITLE});

        }
        else {
            DBHelperyt helper = new DBHelperyt(context);
            var database = helper.getWritableDatabase();
            long newRowId = database.insert(YTEntry.TABLE_NAME, null, values);//thêm vào csdl

        }

    }


    public GoogleNews readFromDatabase(Context context) {

        DBHelperyt helper = new DBHelperyt(context);
        var database = helper.getReadableDatabase();//lấy csdl

        String[] projection = {
                YTEntry.COLUMN_NAME_TITLE,
                YTEntry.COLUMN_NAME_DC_ANH,
                YTEntry.COLUMN_NAME_DUONG_DAN,
                YTEntry.COLUMN_NAME_MO_TA,
                YTEntry.COLUMN_NAME_TG_NHAN,
                YTEntry.COLUMN_NAME_IMG
        };

        String sortOrder =
                YTEntry.COLUMN_NAME_TG_NHAN + " DESC";

        Cursor cursor = database.query(//tra sqlite từng dòng
                YTEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        ArrayList<GoogleNewsItem> items = new ArrayList<>();
        while(cursor.moveToNext()) {// lấy từng dòng của cột
            String itemTitle = cursor.getString(//lấy từng giá trị của cột
                    cursor.getColumnIndexOrThrow(YTEntry.COLUMN_NAME_TITLE));
            String itemLink = cursor.getString(
                    cursor.getColumnIndexOrThrow(YTEntry.COLUMN_NAME_DUONG_DAN));
            String itemDescription = cursor.getString(
                    cursor.getColumnIndexOrThrow(YTEntry.COLUMN_NAME_MO_TA));
            String itemImgUrl = cursor.getString(
                    cursor.getColumnIndexOrThrow(YTEntry.COLUMN_NAME_DC_ANH));
            String imgLoc = cursor.getString(
                    cursor.getColumnIndexOrThrow(YTEntry.COLUMN_NAME_IMG));

            File file = new File(imgLoc);//tạo 1 đối tượng để đọc file từ vị trí hình ảnh
            byte[] data = new byte[(int) file.length()];
            try {
                BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));//tạo đối tượng để đọc
                buf.read(data, 0, data.length);//đọc dữ liệu và truyền vào mảng data
                buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            var img = BitmapFactory.decodeByteArray(data, 0, data.length);//biến mảng chưas dư liệu đó thành ảnh

            GoogleNewsItem ggNewItem = new GoogleNewsItem(itemTitle, itemLink,itemDescription,itemImgUrl, img);//tạo biến theo dạng GoogleNewsItem
            items.add(ggNewItem);
        }
        cursor.close();
        return new GoogleNews(items);
    }



}
