package com.android.example.sportify;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_news);
        ViewPager viewPager = findViewById(R.id.articleViewPager);
        ArticleFragmentAdapter articleFragmentAdapter = new ArticleFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(articleFragmentAdapter);
    }
}
