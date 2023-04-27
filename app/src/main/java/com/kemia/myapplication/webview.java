package com.kemia.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.kemia.myapplication.Adapter.TruyenDuLieu;
import com.kemia.myapplication.Data.Database;
import com.kemia.myapplication.DataYT.Databaseyt;
import com.kemia.myapplication.Fetch.GoogleNewsItem;

public class webview extends Activity {
    WebView wb;
    Button close;
    Button btlike;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");
        wb = findViewById(R.id.webViewContent);
        wb.getSettings().setSupportZoom(true);
        wb.getSettings().setBuiltInZoomControls(true);

        close = findViewById(R.id.wvClose);
        btlike = findViewById(R.id.btlike);

        wb.getSettings().setJavaScriptEnabled(true);
        wb.setWebViewClient(new WebViewClient(){});

        wb.loadUrl(url);

        close.setOnClickListener(view -> {
            finish();
        });

        btlike.setOnClickListener(view -> {
            Databaseyt db = new Databaseyt();
            GoogleNewsItem ggNewItemHienTai = TruyenDuLieu.googleNewsItem;
            db.addNewsItem(ggNewItemHienTai, getApplicationContext());
        });
    }
}
