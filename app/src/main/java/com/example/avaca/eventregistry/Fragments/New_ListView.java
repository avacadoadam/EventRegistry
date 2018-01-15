package com.example.avaca.eventregistry.Fragments;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.avaca.eventregistry.DisplayNewsFragmentPager;
import com.example.avaca.eventregistry.NewsFromat.BaseNews;
import com.example.avaca.eventregistry.R;
import java.util.ArrayList;

/**
 * Created by avaca on 1/11/2018.
 */

public class New_ListView extends Fragment {

    private Spinner CatorgorySpinner;
    private ListView listview;
    private ProgressBar progressbar;
    private ArrayList<BaseNews> News = null;
    DisplayNewsFragmentPager activty;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listnewsxml,container,false);
        activty = (DisplayNewsFragmentPager)getActivity();
        //May new improvment
        News = activty.GETNews();
        Log.d("LIST", "onCreateView: " + News.get(2).getBody());
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressbar = (ProgressBar)view.findViewById(R.id.progressBar2);
        CatorgorySpinner =  (Spinner)view.findViewById(R.id.CatorgorySpinner_NewsList);
        listview = (ListView)view.findViewById(R.id.ListviewNews);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),R.array.Cateogory,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CatorgorySpinner.setAdapter(adapter);
        String Body[] = new String[News.size()];
        Log.d("New_ListView", "onViewCreated: ASDFasfaF and size " + News.size() );
        for (int i = 0; i <News.size() ; i++) {
            Body[i] = News.get(i).getTitle();
            Log.d("Checking Newgettitle", "onViewCreated: " + Body[i]);
        }
        ArrayAdapter<CharSequence> NewsArticles = new ArrayAdapter<CharSequence>(view.getContext(),android.R.layout.simple_list_item_1,android.R.id.text1,Body);
        listview.setAdapter(NewsArticles);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                activty.SetArticleSelected(position);
                activty.getPager().setCurrentItem(2,true);


                }
        });

    }



    public static New_ListView create(){
         New_ListView listview=new New_ListView();
        return listview;
    }


}

