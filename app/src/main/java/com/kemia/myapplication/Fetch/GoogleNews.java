package com.kemia.myapplication.Fetch;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Node;

import java.util.ArrayList;


public class GoogleNews {// chứa tổng dữ liệu thông tin của toàn bộ api

    private ArrayList<GoogleNewsItem> items = new ArrayList<>();

    public GoogleNews(JSONArray array) {
        try {
            for (int i = 0; i < array.length(); i++) {
                var jsonObject = array.getJSONObject(i);// lấy đối tượng json object trong mảng
                GoogleNewsItem item = new GoogleNewsItem(jsonObject);// tạo 1 đối tượng ggnewitem và truyền jsonobject vào hàm khỏi tạo của gg newitem
                items.add(item);//bỏ vô đối tượng ggnewitiem
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public GoogleNews(ArrayList<GoogleNewsItem> items) {
        this.items = items;
    }

    public ArrayList<GoogleNewsItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<GoogleNewsItem> items) {
        this.items = items;
    }

}
