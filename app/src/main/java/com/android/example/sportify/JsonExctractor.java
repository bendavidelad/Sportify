package com.android.example.sportify;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonExctractor {

    public static ArrayList<ArticlesItem> parseResult(java.lang.String jsonString) {
        ArrayList<ArticlesItem> mListData = new ArrayList<>();
        try {
            JSONObject response = new JSONObject(jsonString);
            JSONArray posts = response.optJSONArray("articles");
            ArticlesItem item;
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                java.lang.String title = post.optString("title");
                java.lang.String image = post.optString("urlToImage");
                java.lang.String description = post.optString("description");
                java.lang.String url = post.optString("url");
                item = new ArticlesItem();
                item.setTitle(title);
                item.setImage(image);
                item.setUrl(url);
                item.setDescription(description);

                mListData.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mListData;
    }
}
