package com.android.example.sportify;

import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    private static int TIME_OUT = 4000; //Time to launch the tutorial activity

    private ViewPager viewPager;
    private ArticleFragmentCollectionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toast.makeText(this,"Swipe left to see more articles..", Toast.LENGTH_LONG).show();

        // get Articles URL list from MainActivity, and wrap it in the adapter later
        Intent intent = getIntent();
        ArrayList<String> articlesUrls = intent.getStringArrayListExtra("urlsList");

        // Set adapter to this activity
        viewPager = findViewById(R.id.pager);
        adapter = new ArticleFragmentCollectionAdapter(getSupportFragmentManager(), articlesUrls);
        viewPager.setAdapter(adapter);
    }
}
