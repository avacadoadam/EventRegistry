package com.example.avaca.eventregistry.NewsFromat;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by avaca on 1/12/2018.
 */

public class News_nonGeo extends BaseNews implements Parcelable{

    public News_nonGeo(String body, String title, NewsType type) {
        super(body, title, type);
    }

    public News_nonGeo(Parcel in){
        in.readDouble();
        in.readDouble();
        this.Body = in.readString();
        this.Title = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(0);
        dest.writeDouble(0);
        dest.writeString(getBody());
        dest.writeString(getTitle());
    }


    public static final Parcelable.Creator<News_nonGeo> CREATOR
            = new Parcelable.Creator<News_nonGeo>() {
        public News_nonGeo createFromParcel(Parcel in) {
            return new News_nonGeo(in);
        }

        @Override
        public News_nonGeo[] newArray(int size) {
            return new News_nonGeo[size];

        }
    };
}
