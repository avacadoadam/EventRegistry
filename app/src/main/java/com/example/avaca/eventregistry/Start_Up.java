package com.example.avaca.eventregistry;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.avaca.eventregistry.NewsFromat.BaseNews;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by avaca on 1/7/2018.
 */

public class Start_Up extends Activity implements GetJSON.CallBack{

    Spinner LuaguageSpinner;
    Spinner ThemeSpinner;
    ProgressBar progressbar;
    Spinner Catorgory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_up);
        LuaguageSpinner = findViewById(R.id.LauguageSpinner);
        ThemeSpinner = findViewById(R.id.Themespinner);
        progressbar = findViewById(R.id.loader);
        Catorgory = findViewById(R.id.CatorgorySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Lauguages,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        LuaguageSpinner.setAdapter(adapter);
        ArrayAdapter<CharSequence> Adapter = ArrayAdapter.createFromResource(this,R.array.Theme,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ThemeSpinner.setAdapter(Adapter);
        ArrayAdapter<CharSequence> adapter2 =  ArrayAdapter.createFromResource(this,R.array.Cateogory,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Catorgory.setAdapter(adapter2);
        progressbar.setVisibility(View.INVISIBLE);
    }


    public void ChangeSlide(View view) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        progressbar.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressbar.setProgress(0,true);
        }
        UrlBuilder_EventRegistry url;
        if(Catorgory.getSelectedItem().equals("Everything")){
            Log.d("ButtonChangeSlide", "ChangeSlide: EVERYTHING TRUE-----------");
            url = new UrlBuilder_EventRegistry("eng");
        }else{
            Log.d("ButtonChangeSlide", "ChangeSlide: NOT EVERTHING FALSE-----------" + Catorgory.getSelectedItem().toString());
            url = new UrlBuilder_EventRegistry("eng",Catorgory.getSelectedItem().toString());
        }
        Log.d("ButtonChangeSlide", "ChangeSlide: " + url.getURL());
        GetJSON json  = new GetJSON(url.getURL(),this);
        Thread thread = new Thread(json);
        thread.start();
    }

    @Override
    public void CallBack(ArrayList<BaseNews> data) {
        Intent intent = new Intent(this,DisplayNewsFragmentPager.class );
        intent.putParcelableArrayListExtra("JsonArray",data);
        if(ThemeSpinner.getSelectedItem().equals("Dark")){
            intent.putExtra("Theme",true);
        }else{
            intent.putExtra("Theme",false);
        }
        startActivity(intent);
    }

    public void ListNews(View view) {



    }
}

