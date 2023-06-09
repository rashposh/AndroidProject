package com.kemia.myapplication.Fetch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class GoogleNewsItem {// chứa dữ liệu của 1 bài báo
    private String title;
    private String link;

    private String pubDate;
    private String description;

    private String imgUrl;
    private Bitmap imgBitMap;


    public GoogleNewsItem(JSONObject jsonObject) {// nhận dữ liệu json và gán thuôc tính vào
        try {
            this.title = jsonObject.getString("title");
            this.description=jsonObject.getString("description");
            this.link=jsonObject.getString("url");
            this.imgUrl=jsonObject.getString("urlToImage");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        fetchImgUrl();
    }

    public GoogleNewsItem(String title, String link, String description, String imgUrl, Bitmap img) {//tạo dạng truyền thống
        this.title = title;
        this.link = link;
        this.description = description;
        this.imgUrl = imgUrl;
        this.pubDate = "";
        this.imgBitMap = img;
    }

    public void fetchImgUrl() {

        try {
            InputStream in = new URL(imgUrl).openStream();// dựa địa chỉ của thuộc tính imgUrl,mở đường truyền và lấy về dữ liệu
            this.imgBitMap = BitmapFactory.decodeStream(in);// dựa vào dữ liệu đó và biến nó thành 1 hình ảnh
        } catch (Exception e) {
            this.imgBitMap = null;
        }

    }

    public Bitmap getImgBitMap() {
        return imgBitMap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setImgBitMap(Bitmap imgBitMap) {
        this.imgBitMap = imgBitMap;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
