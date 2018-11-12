package com.android.example.sportify;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {

    private WebView articleView;

    public ArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View articleFragment =  inflater.inflate(R.layout.fragment_article, container, false);
        articleView = articleFragment.findViewById(R.id.webDisplay);

        // Get Urls list from NewsActivity
        String articleUrl = getArguments().getString(getString(R.string.articleUrlKey));

        articleView.setWebViewClient(new WebViewClient());
        articleView.loadUrl(articleUrl);

        return articleFragment;
    }

}
