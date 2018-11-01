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
    private List<ArticlesItem> articles;
    private Map<String, List<ArticlesItem>> allArticles;
    private final String BASEBALL = "baseball";
    private final String BASKETBALL = "basketball";
    private final String FOOTBALL = "football";
    private final String SOCCER = "soccer";
    private final String SWIMMING = "swimming";
    private final String JUDO = "judo";
    private final String BOXING = "boxing";
    private final String TENNIS = "tennis";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allArticles = new HashMap<>();
        allArticles.put(BASEBALL, new ArrayList<ArticlesItem>());
        allArticles.put(BASKETBALL, new ArrayList<ArticlesItem>());
        allArticles.put(FOOTBALL, new ArrayList<ArticlesItem>());
        allArticles.put(SOCCER, new ArrayList<ArticlesItem>());
        allArticles.put(SWIMMING, new ArrayList<ArticlesItem>());
        allArticles.put(JUDO, new ArrayList<ArticlesItem>());
        allArticles.put(BOXING, new ArrayList<ArticlesItem>());
        allArticles.put(TENNIS, new ArrayList<ArticlesItem>());


        prefixUrl = "https://newsapi.org/v2/everything?q=";
        postfixUrl = "&apiKey=74c30136f8a043699c626830c267dc87";

        AsyncHttpTask asyncHttpTask = new AsyncHttpTask();
        asyncHttpTask.execute(prefixUrl, postfixUrl);
//        try {
//            asyncHttpTask.get();
//        } catch (Exception exp){
//            System.out.println(exp.getStackTrace());
//        }

    }

    public void sendNewsCategory(View view) {
        Intent intent = new Intent(this, NewsActivity.class);
        String newsCategory = "";
        switch(view.getId()){
            case R.id.baseballButton:
                newsCategory = allArticles.get(BASEBALL).get(0).getTitle();
                break;
            case R.id.basketballButton:
                newsCategory = allArticles.get(BASKETBALL).get(0).getTitle();
                break;
            case R.id.soccerButton:
                newsCategory = allArticles.get(SOCCER).get(0).getTitle();
                break;
            case R.id.boxingButton:
                newsCategory = allArticles.get(BOXING).get(0).getTitle();
                break;
            case R.id.swimmingButton:
                newsCategory = allArticles.get(SWIMMING).get(0).getTitle();
                break;
            case R.id.tennisButton:
                newsCategory = allArticles.get(TENNIS).get(0).getTitle();
                break;
            case R.id.footballButton:
                newsCategory = allArticles.get(FOOTBALL).get(0).getTitle();
                break;
            case R.id.judoButton:
                newsCategory = allArticles.get(JUDO).get(0).getTitle();
                break;
        }
        intent.putExtra("title", newsCategory);
        startActivity(intent);
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
