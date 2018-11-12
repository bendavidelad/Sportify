package com.android.example.sportify;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    private ViewPager newsViewer;
    private ArticleFragmentCollectionAdapter articleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        Toast.makeText(this,getString(R.string.tutorialMessage), Toast.LENGTH_LONG).show();

        // get Articles URL list from MainActivity, and wrap it in the adapter later
        Intent fragmentPage = getIntent();
        ArrayList<String> articlesUrls = fragmentPage.getStringArrayListExtra("urlsList");

        // Set adapter to this activity
        newsViewer = findViewById(R.id.pager);
        articleAdapter = new ArticleFragmentCollectionAdapter(getSupportFragmentManager(), articlesUrls);
        newsViewer.setAdapter(articleAdapter);
    }
}
