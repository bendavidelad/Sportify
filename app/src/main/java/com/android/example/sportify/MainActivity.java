package com.android.example.sportify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // comment
        //Orwa's comment
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    public void baseballNews(View view) {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }
}
