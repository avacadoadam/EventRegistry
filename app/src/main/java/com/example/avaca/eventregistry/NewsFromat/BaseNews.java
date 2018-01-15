package com.example.avaca.eventregistry.NewsFromat;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import static com.example.avaca.eventregistry.NewsFromat.NewsType.EventRegistry_Arictle_NoGeoCoords;

/**
 * Created by avaca on 1/12/2018.
 */

public abstract class BaseNews implements Parcelable{
    protected String Body;
    protected String Title;
    protected NewsType type;

    public BaseNews(String body, String title, NewsType type) {
        Body = body;
        Title = title;
        this.type = type;
    }
     public BaseNews(Parcel in){
        in.readDouble();
        in.readDouble();
        Body = in.readString();
        Title = in.readString();
     }

    protected BaseNews() {
    }


    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public NewsType getType() {
        return type;
    }

    public void setType(NewsType type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
    public static final Parcelable.Creator<News_Geo> CREATOR = null;

}