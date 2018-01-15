package com.example.avaca.eventregistry.NewsFromat;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by avaca on 1/12/2018.
 */

public class News_Geo extends BaseNews implements Parcelable{

    private LatLng coords;
    public News_Geo(String body, String title, NewsType type,double lat,double lng) {
        super(body, title, type);
        if(type == NewsType.EventRegistry_Arictle_GeoCoords){
            coords = new LatLng(lat,lng);
        }
    }

    public News_Geo(String body, String title, NewsType type,LatLng coords) {
        super(body, title, type);
        if(type == NewsType.EventRegistry_Arictle_GeoCoords){
            this.coords = coords;
        }
    }

    protected News_Geo(Parcel in) {
        coords = new LatLng(in.readDouble(),in.readDouble());
        this.Body = in.readString();
        this.Title = in.readString();
    }

    public static final Parcelable.Creator<News_Geo> CREATOR
            = new Parcelable.Creator<News_Geo>() {
        public News_Geo createFromParcel(Parcel in) {
            return new News_Geo(in);
        }

        @Override
        public News_Geo[] newArray(int size) {
            return new News_Geo[size];

        }
    };

    public LatLng getCoords() {
        return coords;
    }

    public void setCoords(LatLng coords) {
        this.coords = coords;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(coords.latitude);
        dest.writeDouble(coords.longitude);
        dest.writeString(Body);
        dest.writeString(Title);
    }
}
