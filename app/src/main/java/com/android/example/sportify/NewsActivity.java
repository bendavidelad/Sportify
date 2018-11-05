package com.android.example.sportify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String newsArticleOne = intent.getStringExtra("newsArticleOne");
        String newsArticleTwo = intent.getStringExtra("newsArticleTwo");
        setContentView(R.layout.activity_news);
        WebView myWebViewOne = findViewById(R.id.newsArticleOne);
        myWebViewOne.loadUrl(newsArticleOne);
        WebView myWebViewTwo = findViewById(R.id.newsArticleTwo);
        myWebViewTwo.loadUrl(newsArticleTwo);

    }
}
