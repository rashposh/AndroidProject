package com.kemia.myapplication;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
