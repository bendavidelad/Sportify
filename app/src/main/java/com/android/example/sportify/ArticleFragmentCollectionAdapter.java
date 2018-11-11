package com.android.example.sportify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ArticleFragmentCollectionAdapter extends FragmentStatePagerAdapter {

    // This is a turn-around: It seems that a Fragment class can't accept more than one bundle,
    // and hence we had two bundles, one to send the articles urls from NewsActivity class
    // to Fragment and the second bundle is the swipe index from the FragmentCollectionAdapter
    // class to Fragment.
    // Our solution was basically to pass urls array from NewsActivity to Adapter in constructor
    // and then we sent all as one bundle.
    private ArrayList<String> articles;

    public ArticleFragmentCollectionAdapter(FragmentManager fm, ArrayList<String> articles) {
        super(fm);
        this.articles = articles;
    }

    @Override
    public Fragment getItem(int index) {

        ArticleFragment articleFragment = new ArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("articleUrl",  this.articles.get(index));
        articleFragment.setArguments(bundle);
        return articleFragment;
    }

    @Override
    /**
     * Defines the maximum number of articles to show in the swap feature.
     */
    public int getCount() {
        return articles.size(); // It should be dependent by the articles size since
                                // Google news doesn't provide a constant number of articles
    }
}
