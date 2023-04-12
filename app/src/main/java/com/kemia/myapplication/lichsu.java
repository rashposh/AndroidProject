package com.kemia.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.textview.MaterialTextView;
import com.kemia.myapplication.Data.Database;
import com.kemia.myapplication.Fetch.GoogleNews;
import com.kemia.myapplication.Fetch.GoogleNewsItem;

import java.util.ArrayList;
import java.util.Objects;

public class lichsu extends AppCompatActivity {


    LinearLayout layout;
    private ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lichsu);
        layout = findViewById(R.id.lsLinear);
        container = findViewById(R.id.lsView);

        Database db = new Database();
        addItem(db.readFromDatabase(getApplicationContext()));

    }

    private void addItem(GoogleNews googleNews) {
        for (var item : googleNews.getItems()) {
            layout.addView(createNewCard(item));
        }
    }
    private View createNewCard(GoogleNewsItem googleNewsItem) {
        // inflate (create) another copy of our custom layout
        LayoutInflater newsInflater = getLayoutInflater();
        View myLayout = newsInflater.inflate(R.layout.new_item, container, false);

        for (View item : getAllChildren(myLayout)) {
            if (item instanceof MaterialTextView) {
                ((MaterialTextView) item).setText(googleNewsItem.getTitle());
            }
            if (item instanceof AppCompatImageView) {
                if (!Objects.isNull(googleNewsItem.getImgBitMap()))
                    ((AppCompatImageView) item).setImageBitmap(googleNewsItem.getImgBitMap());
            }
        }

        myLayout.setOnClickListener(view -> {
            var intent = new Intent(this, webview.class);
            intent.putExtra("url", googleNewsItem.getLink());
            startActivity(intent);

            Database db = new Database();
            db.addNewsItem(googleNewsItem, getApplicationContext());

        });

        return myLayout;
    }

    private ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup viewGroup = (ViewGroup) v;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            View child = viewGroup.getChildAt(i);

            //Do not add any parents, just add child elements
            result.addAll(getAllChildren(child));
        }
        return result;
    }
}