package com.android.example.sportify;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {

    private WebView webView;

    public ArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View articleFragment =  inflater.inflate(R.layout.fragment_article, container, false);
        webView = articleFragment.findViewById(R.id.webDisplay);

        // Get Urls list from NewsActivity
        String articleUrl = getArguments().getString(getString(R.string.articleUrlKey));

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(articleUrl);

        return articleFragment;
    }

}
