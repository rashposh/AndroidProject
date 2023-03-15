package com.kemia.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaoMoiActivity extends AppCompatActivity {
//    TextView bmTextView;
    Button bmButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baomoi);
        System.out.println("FU");
//        bmTextView = findViewById(R.id.bmTextView);
//        bmButton = findViewById(R.id.bmButton);
//        bmButton.setOnClickListener(view -> bmTextView.setText("asc"));

    }
}
