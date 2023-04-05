package com.kemia.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.Nullable;

public class webview extends Activity {
    WebView wb;
    Button close;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");
        wb = findViewById(R.id.webViewContent);
        close = findViewById(R.id.wvClose);
//        System.out.println(wb);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setWebViewClient(new WebViewClient(){});

        wb.loadUrl(url);

        close.setOnClickListener(view -> {
            finish();
        });
    }
}
