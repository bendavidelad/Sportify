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
    private Map<String, List<ArticlesItem>> allArticles;
    private final String BASEBALL = "everything?sources=the-new-york-times&q=baseball";
    private final String BASKETBALL = "everything?sources=espn&q=basketball";
    private final String FOOTBALL = "everything?q=football";
    private final String SOCCER = "everything?sources=bbc-sport&q=soccer";
    private final String SWIMMING = "everything?sources=fox-sports&q=swimming";
    private final String JUDO = "everything?sources=the-jerusalem-post&q=judo";
    private final String BOXING = "everything?sources=bleacher-report&q=boxing";
    private final String TENNIS = "everything?q=tennis";


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

        AsyncHttpTask asyncHttpTask = new AsyncHttpTask();
        asyncHttpTask.execute(prefixUrl, postfixUrl);
        try {
            asyncHttpTask.get();
        } catch (Exception exp){
            System.out.println(exp.getStackTrace());
        }
        setContentView(R.layout.activity_main);

    }

    public void sendNewsCategory(View view) {
        Intent intent = new Intent(this, NewsActivity.class);
        String newsArticleOne = "";
        String newsArticleTwo = "";
        switch(view.getId()){
            case R.id.baseballButton:
                newsArticleOne = allArticles.get(BASEBALL).get(0).getUrl();
                newsArticleTwo = allArticles.get(BASEBALL).get(1).getUrl();
                break;
            case R.id.basketballButton:
                newsArticleOne = allArticles.get(BASKETBALL).get(0).getUrl();
                newsArticleTwo = allArticles.get(BASKETBALL).get(1).getUrl();
                break;
            case R.id.soccerButton:
                newsArticleOne = allArticles.get(SOCCER).get(0).getUrl();
                newsArticleTwo = allArticles.get(SOCCER).get(1).getUrl();
                break;
            case R.id.boxingButton:
                newsArticleOne = allArticles.get(BOXING).get(0).getUrl();
                newsArticleTwo = allArticles.get(BOXING).get(1).getUrl();
                break;
            case R.id.swimmingButton:
                newsArticleOne = allArticles.get(SWIMMING).get(0).getUrl();
                newsArticleTwo = allArticles.get(SWIMMING).get(1).getUrl();
                break;
            case R.id.tennisButton:
                newsArticleOne = allArticles.get(TENNIS).get(0).getUrl();
                newsArticleTwo = allArticles.get(TENNIS).get(1).getUrl();
                break;
            case R.id.footballButton:
                newsArticleOne = allArticles.get(FOOTBALL).get(0).getUrl();
                newsArticleTwo = allArticles.get(FOOTBALL).get(1).getUrl();
                break;
            case R.id.judoButton:
                newsArticleOne = allArticles.get(JUDO).get(0).getUrl();
                newsArticleTwo = allArticles.get(JUDO).get(1).getUrl();
                break;
        }
        intent.putExtra("newsArticleOne", newsArticleOne);
        intent.putExtra("newsArticleTwo", newsArticleTwo);
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
