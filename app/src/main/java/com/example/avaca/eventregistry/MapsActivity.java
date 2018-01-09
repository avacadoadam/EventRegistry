package com.example.avaca.eventregistry;

import android.os.Parcelable;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    ArrayList<DefaultNewsFormat> News = new ArrayList<DefaultNewsFormat>();
    private static final String TAG = MapsActivity.class.getSimpleName();
    boolean MapStyleDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        News = getIntent().getParcelableArrayListExtra("JsonArray");
        MapStyleDark = getIntent().getBooleanExtra("Theme",true);
//        getIntent().getSerializableExtra("JsonArray");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);
        if(MapStyleDark) {
            if (mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyledark))) {
                Log.e(TAG, "onMapReady: Worked Map style applied");
            }
        }
        if(!(News == null)){
            for (int i = 0; i < News.size(); i++) {
                mMap.addMarker(new MarkerOptions().position(News.get(i).getCoords()).title(News.get(i).getBody()));
                mMap.setOnMarkerClickListener(this);
            }
        }


        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(0,0)));
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show();
        return false;
    }
}
