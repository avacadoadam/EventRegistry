package com.example.avaca.eventregistry;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by avaca on 1/9/2018.
 */

public class NewsList extends Activity implements GetJSON.CallBack{
    //Amount of articles change updated needed
    private ArrayList<String> NewsArrayTitle = new ArrayList<String>();
    private ArrayList<String> NewsArrayBody = new ArrayList<String>();
    private Spinner CatorgorySpinner;
    private ListView listview;
    private ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listnews);
        progressbar = (ProgressBar)findViewById(R.id.progressBar2);
        CatorgorySpinner =  (Spinner)findViewById(R.id.CatorgorySpinner_NewsList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Cateogory,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CatorgorySpinner.setAdapter(adapter);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        listview = (ListView)findViewById(R.id.ListviewNews);
        UrlBuilder_EventRegistry url = new UrlBuilder_EventRegistry("eng");
        try {
            JSONObject articles = (JSONObject)GetJSON.retreve(url.getURL()).get("articles");
            JSONArray Results = (JSONArray) articles.get("results");
            int Length = Results.length();
            Log.d("asdfgasdfgsadfga", "asdffgasdgasdgasdg--------------: length" + Length);
            for (int i = 0; i < Length ; i++) {
                if(!Results.getJSONObject(i).isNull("title")){
                    NewsArrayTitle.add(Results.getJSONObject(i).getString("title"));
                }
                if(!Results.getJSONObject(i).isNull("body")){
                    NewsArrayBody.add(Results.getJSONObject(i).getString("title"));
                }
            }
            ArrayAdapter<String> NewsArticles = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,NewsArrayTitle);
            listview.setAdapter(NewsArticles);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //Updates the arraylist and listview
    public void ParseJSON(JSONObject result){
        try {
            JSONObject articles = (JSONObject)result.get("articles");
            JSONArray Results = (JSONArray) articles.get("results");
            int Length = Results.length();
            Log.d("asdfgasdfgsadfga", "asdffgasdgasdgasdg--------------: length" + Length);
            for (int i = 0; i < Length ; i++) {
                if(!Results.getJSONObject(i).isNull("title")){
                    NewsArrayTitle.add(Results.getJSONObject(i).getString("title"));
                }
                if(!Results.getJSONObject(i).isNull("body")){
                    NewsArrayBody.add(Results.getJSONObject(i).getString("title"));
                }
            }
            ArrayAdapter<String> NewsArticles = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,NewsArrayTitle);
            listview.setAdapter(NewsArticles);
        }catch (JSONException e) {
        e.printStackTrace();
    }
    }
    
    public void NewSearch(View view) {
        if(!CatorgorySpinner.getSelectedItem().equals("Everything")){
            listview.setVisibility(View.INVISIBLE);
            listview.setLayoutParams(new AbsListView.LayoutParams(0,0));
            progressbar.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200,200);
            progressbar.setLayoutParams(params);
            UrlBuilder_EventRegistry url = new UrlBuilder_EventRegistry("eng",CatorgorySpinner.getSelectedItem().toString());
            GetJSON getjson = new GetJSON(url.getURL(),this);
            Thread thread = new Thread(getjson);

        }


    }

    @Override
    public void CallBack(ArrayList<DefaultNewsFormat> data) {

    }
}//End of NewsList Class
