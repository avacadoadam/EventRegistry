package com.example.avaca.eventregistry.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.avaca.eventregistry.DisplayNewsFragmentPager;
import com.example.avaca.eventregistry.FragmentAdapter;
import com.example.avaca.eventregistry.NewsFromat.BaseNews;
import com.example.avaca.eventregistry.NewsFromat.NewsType;
import com.example.avaca.eventregistry.R;


/**
 * Created by avaca on 1/13/2018.
 */

public class Full_Article extends Fragment {

    private static final String TAG = "FulleArticleFragment";
    TextView Bodytext;
    TextView Titletext;
    Button MapButton;
    public static String FragmentTAG;
//    BaseNews News;
    DisplayNewsFragmentPager activty;
    


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
             activty = (DisplayNewsFragmentPager) getActivity();
//            News = getIntent().getParcelableExtra("Json");
        } catch (Exception e) {
            Log.e(TAG, "onCreate: News article passed from intent is empty", e);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.news_article,container,false);
        Log.d(TAG, "onCreateView: SADGSADGSADGSADG");
       return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bodytext = (TextView) view.findViewById(R.id.BodyText);
        Titletext = (TextView) view.findViewById(R.id.TitleText);
        MapButton = (Button) view.findViewById(R.id.GoToMap);
        //Gets Passed a news article

    }

    public void DisplayArticle(BaseNews News){
        if(News != null) {
            if (News.getType() == NewsType.EventRegistry_Arictle_GeoCoords) {
                MapButton.setVisibility(View.VISIBLE);
                MapButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ChangeToMap();
                    }
                });
            }
            Titletext.setText(News.getBody());
            Bodytext.setText(News.getTitle());
        }
    }

    public void ChangeToMap() {
    }

    public static Full_Article create(){
        Full_Article article = new Full_Article();
        return article;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume:  Is called");
    }


}