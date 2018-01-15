package com.example.avaca.eventregistry;
import android.util.Log;

import com.example.avaca.eventregistry.NewsFromat.BaseNews;
import com.example.avaca.eventregistry.NewsFromat.NewsType;
import com.example.avaca.eventregistry.NewsFromat.News_Geo;
import com.example.avaca.eventregistry.NewsFromat.News_nonGeo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;



/**
 * Created by avaca on 1/7/2018.
 */

public class GetJSON implements Runnable{

    interface CallBack{
        public void CallBack(ArrayList<BaseNews> data);
    }

    private ArrayList<BaseNews> News;
    private String url;
    private CallBack callback;

    public GetJSON(String url,CallBack callBack){
        this.url = url;
        this.callback = callBack;


    }//End Of Constructor
    /**
     *
     * @param url_input the url Of the api query
     * @return Will return JSONObject
     * @throws IOException Will Throw if could Not Connect To Url
     * @throws JSONException Will Throw if JSON is not valid check for callback function
     */
    public static JSONObject retreve(String url_input) throws IOException, JSONException {
    URL url = new URL(url_input);
    URLConnection urlConn = url.openConnection();
    BufferedReader buffread = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
    String result = buffread.readLine();
    Log.d("GetJson --", "retreve: " + result);
    return (JSONObject)new JSONTokener(result).nextValue();
    }


    /**
     *
      * @param json_obj Json Object That contains json from event registry without callback
     * @return
     * @throws JSONException ,Ariticles may Not contain GeoLocations in which case will
     * IGNORE and log when so
     */
    public ArrayList<BaseNews> Parse_Event_Registry(JSONObject json_obj) throws JSONException {
        ArrayList<BaseNews> News = new ArrayList<BaseNews>();
        JSONObject articles = (JSONObject) json_obj.get("articles");
        JSONArray results = (JSONArray) articles.get("results");
        for (int i = 0; i < results.length(); i++) {
            try {
                JSONObject ArticleResult;
                if (!(results.getJSONObject(i).isNull("location"))) {
                    ArticleResult = results.getJSONObject(i).getJSONObject("location");
                    News_Geo news = new News_Geo(results.getJSONObject(i).getString("body"),results.getJSONObject(i).getString("title"),NewsType.EventRegistry_Arictle_GeoCoords,ArticleResult.getDouble("lat"),ArticleResult.getDouble("long"));
                    News.add(news);
                }else{
                    News_nonGeo news = new News_nonGeo(results.getJSONObject(i).getString("body"),results.getJSONObject(i).getString("title"), NewsType.EventRegistry_Arictle_NoGeoCoords);
                    News.add(news);
                    Log.d("GetJson --", "Parse_Event_Registry:No locations " + i);
                }
            } catch (Exception e) {
                Log.d("NoGeoLocation", "GetJSON: Error in parsing JSON");
            }
        }//Close For loop
        return News;
    }//End of Class



public ArrayList<BaseNews> Get_ListOfAritcles_withGeoCoords(){
        return this.News;
}

@Override
public void run() {
    try {
        News = Parse_Event_Registry(retreve(url));
        callback.CallBack(News);
    }catch (Exception e){
        e.printStackTrace();
    }

}
}//End of GetJSON class
