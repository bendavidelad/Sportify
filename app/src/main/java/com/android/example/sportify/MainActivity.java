package com.android.example.sportify;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String prefixUrl;
    private String postfixUrl;
    private Map<String, ArrayList<ArticlesItem>> allArticles;
    private final String BASEBALL = "everything?sources=the-new-york-times&q=baseball";
    private final String BASKETBALL = "everything?sources=espn&q=basketball";
    private final String FOOTBALL = "everything?sources=the-new-york-times&q=football";
    private final String SOCCER = "everything?sources=bbc-sport&q=soccer";
    private final String SWIMMING = "everything?sources=fox-sports&q=swimming";
    private final String JUDO = "everything?sources=the-jerusalem-post&q=judo";
    private final String BOXING = "everything?sources=bleacher-report&q=boxing";
    private final String TENNIS = "everything?sources=bbc-sport&q=tennis";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allArticles = new HashMap<>();
        allArticles.put(BASEBALL, new ArrayList<ArticlesItem>());
        allArticles.put(BASKETBALL, new ArrayList<ArticlesItem>());
        allArticles.put(FOOTBALL, new ArrayList<ArticlesItem>());
        allArticles.put(SOCCER, new ArrayList<ArticlesItem>());
        allArticles.put(SWIMMING, new ArrayList<ArticlesItem>());
        allArticles.put(JUDO, new ArrayList<ArticlesItem>());
        allArticles.put(BOXING, new ArrayList<ArticlesItem>());
        allArticles.put(TENNIS, new ArrayList<ArticlesItem>());


        prefixUrl = "https://newsapi.org/v2/";
        postfixUrl = "&apiKey=74c30136f8a043699c626830c267dc87";

        AsyncHttpTask asyncGetArticles = new AsyncHttpTask();
        asyncGetArticles.execute(prefixUrl, postfixUrl);
        try {
            asyncGetArticles.get();
        } catch (Exception exp){
            System.out.println(exp.getStackTrace());
        }
        setContentView(R.layout.activity_main);

    }

    public void sendNewsCategory(View view) {
        Intent newsPage = new Intent(this, NewsActivity.class);
        ArrayList<ArticlesItem> articleItems = new ArrayList<ArticlesItem>();
        switch(view.getId()){
            case R.id.baseballButton:
                articleItems = allArticles.get(BASEBALL);
                break;
            case R.id.basketballButton:
                articleItems = allArticles.get(BASKETBALL);
                break;
            case R.id.soccerButton:
                articleItems = allArticles.get(SOCCER);
                break;
            case R.id.boxingButton:
                articleItems = allArticles.get(BOXING);
                break;
            case R.id.swimmingButton:
                articleItems = allArticles.get(SWIMMING);
                break;
            case R.id.tennisButton:
                articleItems = allArticles.get(TENNIS);
                break;
            case R.id.footballButton:
                articleItems = allArticles.get(FOOTBALL);
                break;
            case R.id.judoButton:
                articleItems = allArticles.get(JUDO);
                break;
        }

        // Send array of urls only in Intent
        ArrayList<String> urlsToSend = new ArrayList<String>();
        for (ArticlesItem article : articleItems) {
            urlsToSend.add(article.getUrl());
        }

        newsPage.putStringArrayListExtra(getString(R.string.urlsListKey), urlsToSend);
        startActivity(newsPage);
    }

    public class AsyncHttpTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            for (String currentArticleCategory : allArticles.keySet()){
                String urlToSend = urls[0]  + currentArticleCategory + urls[1];
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(urlToSend);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    String response = streamToString(urlConnection.getInputStream());
                    JsonExctractor jsonExtractor = new JsonExctractor();
                    allArticles.put(currentArticleCategory, jsonExtractor.parseResult(response));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }

    String streamToString(InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String data;
        String result = "";
        while ((data = bufferedReader.readLine()) != null) {
            result += data;
        }
        if (stream != null) {
            stream.close();
        }
        return result;
    }



}