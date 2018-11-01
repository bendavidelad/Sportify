package com.android.example.sportify;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String News_URL;
    private List<ArticlesItem> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        News_URL = "https://newsapi.org/v2/everything?sources=ynet&q=%D7%9B%D7%93%D7%95%D7%A8%D7%92%D7%9C&apiKey=74c30136f8a043699c626830c267dc87";
        new AsyncHttpTask().execute(News_URL);

        System.out.println("------------------------------------------------------");
        for(ArticlesItem article : articles) {
            System.out.println(article.getTitle());

        }
        System.out.println("------------------------------------------------------");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void baseballNews(View view) {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }

    public class AsyncHttpTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                String response = streamToString(urlConnection.getInputStream());
                JsonExctractor jsonExtractor = new JsonExctractor();
                articles = jsonExtractor.parseResult(response);;
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
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
