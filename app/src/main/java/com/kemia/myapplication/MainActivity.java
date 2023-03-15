package com.kemia.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    Button more;
    SearchView search;
    Button bao1;
    Button trend;
    Button video;
    Button like;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        more = findViewById(R.id.more);
        search = findViewById(R.id.search);
        bao1 = findViewById(R.id.bao1);
        trend = findViewById(R.id.trend);
        like = findViewById(R.id.like);
        video = findViewById(R.id.video);
        bao1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BaoMoiActivity.class));
            }
        }
        );
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VideoActivity.class));
            }
        }
        );
        trend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {setContentView(R.layout.baomoi);}
        }
        );
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {setContentView(R.layout.baomoi);}
        }
        );
    }
}