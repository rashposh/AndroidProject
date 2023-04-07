package com.kemia.myapplication.Fetch;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Node;

import java.util.ArrayList;


public class GoogleNews {

    private ArrayList<GoogleNewsItem> items = new ArrayList<>();

    public GoogleNews(JSONArray array) {
        try {
            for (int i = 0; i < array.length(); i++) {
                var jsonObject = array.getJSONObject(i);
                GoogleNewsItem item = new GoogleNewsItem(jsonObject);// tách mảng đó ra
                items.add(item);//bỏ vô đối tượng ggnewitiem
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
//        for (int i = 0; i < array.length(); i++) {
////            try {
////                GoogleNewsItem googleNewsItem = new GoogleNewsItem(array.get(i));
////            } catch (JSONException e) {
////                throw new RuntimeException(e);
////            }
//        }
//        items.add(googleNewsItem);
    }

    public ArrayList<GoogleNewsItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<GoogleNewsItem> items) {
        this.items = items;
    }

}
