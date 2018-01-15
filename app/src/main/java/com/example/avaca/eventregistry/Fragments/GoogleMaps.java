package com.example.avaca.eventregistry.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.avaca.eventregistry.NewsFromat.BaseNews;
import com.example.avaca.eventregistry.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

/**
 * Created by avaca on 1/11/2018.
 */

public class GoogleMaps extends Fragment implements GoogleMap.OnMarkerClickListener  {
    private GoogleMap mMap;
    MapView mapview;
    private ArrayList<BaseNews> news;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() !=null){
            news = (ArrayList<BaseNews>)getArguments().get("json");
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mapfragment,container,false);
        mapview = (MapView)view.findViewById(R.id.mapView);
        mapview.getMapAsync(new OnMapReadyCallback(){

            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("GOOGLEMAPSS", "onMapReady:called ");
                mMap = googleMap;

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0,0),0f));
                mMap.getUiSettings().setMapToolbarEnabled(false);
                mMap.getUiSettings().setScrollGesturesEnabled(false);
                mMap.getUiSettings().setScrollGesturesEnabled(false);

            }

        });
        mapview.onCreate(savedInstanceState);
        mapview.onResume();
        MapsInitializer.initialize(getActivity().getApplicationContext());
        return view;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }


    @Override
    public void onResume() {
        super.onResume();
        mapview.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapview.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapview.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapview.onLowMemory();
    }

    public static GoogleMaps create(){
        GoogleMaps maps = new GoogleMaps();
        return maps;
    }
}
