package com.example.avaca.eventregistry;

import android.os.Parcel;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by avaca on 1/12/2018.
 */

public abstract class NewsFormat {
    private LatLng coords = null;
    private String Body;
    private String Title;
    boolean HasGeoCoords;
    //Constructor



    NewsFormat(LatLng coords,String Body,String Title){
        this.Body = Body;
        this.Title = Title;
        HasGeoCoords = true;
        this.coords = coords;

    }

    NewsFormat(String Body, String Title){
        this.Body = Body;
        this.Title = Title;
        HasGeoCoords = false;

    }
    NewsFormat(double lat,double lng,String Body,String Title){
        this(new LatLng(lat,lng),Body,Title);
    }

    NewsFormat(Parcel in) {
        double lat = in.readDouble();
        double lng = in.readDouble();
        if(lat == 0.0d||lng == 0.0d){
            setBody(in.readString());
            setTitle(in.readString());
        }

    }
    //End of Construtors
    //Getters and setters

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

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


