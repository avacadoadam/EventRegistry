package com.example.avaca.eventregistry;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;


/**
 * Created by avaca on 1/7/2018.
 */

public class GetJSON{


   private ArrayList<DefaultNewsFormat> ListOfAritcles_withGeoCoords;

    public GetJSON(String url){
        try {
            ListOfAritcles_withGeoCoords = Parse_Event_Registry(retreve(url));
        }catch (Exception e){
            e.printStackTrace();
        }


    }//End Of Constructor


    /**
     *
     * @param url_input the url Of the api query
     * @return Will return JSONObject
     * @throws IOException Will Throw if could Not Connect To Url
     * @throws JSONException Will Throw if JSON is not valid check for callback function
     */
    public JSONObject retreve(String url_input) throws IOException, JSONException {
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
    public ArrayList<DefaultNewsFormat> Parse_Event_Registry(JSONObject json_obj) throws JSONException {
        ArrayList<DefaultNewsFormat> ListOfAritcles_withGeoCoords = new ArrayList<DefaultNewsFormat>();
        JSONObject articles = (JSONObject) json_obj.get("articles");
        JSONArray results = (JSONArray) articles.get("results");
        for (int i = 0; i < results.length(); i++) {
            try {
                JSONObject location;
                if (!(results.getJSONObject(i).isNull("location"))) {
                    location = results.getJSONObject(i).getJSONObject("location");
                    DefaultNewsFormat news = new DefaultNewsFormat(location.getDouble("lat"),
                            location.getDouble("long")
                            , (String) results.getJSONObject(i).get("body"));
                    ListOfAritcles_withGeoCoords.add(news);
                }else{
                    Log.d("GetJson --", "Parse_Event_Registry:No locations " + i);
                }
            } catch (Exception e) {
                Log.d("NoGeoLocation", "GetJSON: Error in parsing JSON");
            }
        }//Close For loop
        return ListOfAritcles_withGeoCoords;
    }//End of Class



public ArrayList<DefaultNewsFormat> Get_ListOfAritcles_withGeoCoords(){
        return this.ListOfAritcles_withGeoCoords;
}
}//End of GetJSON class

//Abstract Class for news format
abstract class NewsDataGeoCoords implements Parcelable{
    private LatLng coords;
    private String Body;
    //Constructor
    NewsDataGeoCoords(LatLng coords,String Body){
        this.coords = coords;
        this.Body = Body;
    }
    NewsDataGeoCoords(double lat,double lng,String Body){
        this(new LatLng(lat,lng),Body);

    }
    //End of Construtors
    //Getters and setters
    public LatLng getCoords() {
        return coords;
    }

    public void setCoords(LatLng coords) {
        this.coords = coords;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }
}//End Of NewsData Abstract Class

class DefaultNewsFormat extends  NewsDataGeoCoords implements Parcelable{

    DefaultNewsFormat(LatLng coords,String Body){
        super(coords,Body);
    }
    DefaultNewsFormat(double lat,double lng,String Body){
        super(lat,lng,Body);

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //Can change to multiDemonsial Array

        dest.writeDouble(getCoords().latitude);
        dest.writeDouble(getCoords().longitude);
        dest.writeString(getBody());
    }

    public static final Parcelable.Creator<DefaultNewsFormat> CREATOR
            = new Parcelable.Creator<DefaultNewsFormat>(){
        public DefaultNewsFormat createFromParcel(Parcel in){
            return new DefaultNewsFormat(in);
        }

        @Override
        public DefaultNewsFormat[] newArray(int size) {
            return new DefaultNewsFormat[size];
        }
    };


    public DefaultNewsFormat(Parcel in) {
        super(in.readDouble(),in.readDouble(),in.readString());

        Log.d(TAG, "DefaultNewsFormat: Parcel Constuctor Called");

    }
}
