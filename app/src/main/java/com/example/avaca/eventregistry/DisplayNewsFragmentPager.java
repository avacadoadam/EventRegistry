package com.example.avaca.eventregistry;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.avaca.eventregistry.NewsFromat.BaseNews;

import java.util.ArrayList;

/**
 * Created by avaca on 1/11/2018.
 */

public class DisplayNewsFragmentPager extends FragmentActivity {
    private static final String TAG = "NewsFragmentPager";
    ArrayList<BaseNews> News;
    boolean MapStyleDark;
    FragmentAdapter Adapter;
    int NewsArticleSelected = -1;
    Fragment frag_ListNews;
    Fragment frag_ArticleMap;
    Fragment frag_Display_Article;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_news);
        News = getIntent().getParcelableArrayListExtra("JsonArray");
        Log.d("Test Intent Pacace", "onCreate:jsonarray " +  News.get(1).getBody());
        MapStyleDark = getIntent().getBooleanExtra("Theme",true);

    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.d(TAG, "onAttachFragment: Called Fragment" );
    }

    public  ArrayList<BaseNews> GETNews(){
        return News;
    }


    public FragmentAdapter getAdapter(){
        return Adapter;
    }
    public int GetArticleSelected(){
        return NewsArticleSelected;
    }
    public void SetArticleSelected(int num){
        if(num > 0){
            NewsArticleSelected = num;
        }
    }
}

