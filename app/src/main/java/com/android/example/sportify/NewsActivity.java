package com.android.example.sportify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String news = intent.getStringExtra("news");
        setContentView(R.layout.activity_news);
        WebView myWebView = findViewById(R.id.news);
        myWebView.loadUrl(news);

    }
}
