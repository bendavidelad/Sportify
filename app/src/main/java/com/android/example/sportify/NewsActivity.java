package com.android.example.sportify;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ArticleFragmentCollectionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // get Articles URL list from MainActivity, and wrap it in the adapter later
        Intent intent = getIntent();
        ArrayList<String> articlesUrls = intent.getStringArrayListExtra("urlsList");

        // Set adapter to this activity
        viewPager = findViewById(R.id.pager);
        adapter = new ArticleFragmentCollectionAdapter(getSupportFragmentManager(), articlesUrls);
        viewPager.setAdapter(adapter);
    }
}
