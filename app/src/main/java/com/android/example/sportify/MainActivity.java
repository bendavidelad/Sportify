package com.android.example.sportify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendNewsCategory(View view) {
        Intent intent = new Intent(this, NewsActivity.class);
        String newsCategory = "";
        switch(view.getId()){
            case R.id.baseballButton:
                newsCategory = "baseball";
                break;
            case R.id.basketballButton:
                newsCategory = "basketball";
                break;
            case R.id.soccerButton:
                newsCategory = "soccer";
                break;
            case R.id.boxingButton:
                newsCategory = "boxing";
                break;
            case R.id.swimmingButton:
                newsCategory = "swimming";
                break;
            case R.id.tennisButton:
                newsCategory = "tennis";
                break;
            case R.id.footballButton:
                newsCategory = "football";
                break;
            case R.id.judoButton:
                newsCategory = "judo";
                break;
        }
        intent.putExtra("news Category", newsCategory);
        startActivity(intent);
    }
}
