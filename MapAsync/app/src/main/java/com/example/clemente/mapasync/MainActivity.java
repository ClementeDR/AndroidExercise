package com.example.clemente.mapasync;


import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;


public class MainActivity extends Activity implements OnMapReadyCallback {

    private double latitude;
    private double longitude;
    private MapFragment mMapFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("latitude", latitude);
        outState.putDouble("longitude", longitude);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
//        if (savedInstanceState != null) {
//            latitude = savedInstanceState.getDouble("latitude");
//            longitude = savedInstanceState.getDouble("longitude");
//        }


        mMapFragment = (MapFragment) getFragmentManager().findFragmentByTag("TAG");
        if (mMapFragment == null);
            mMapFragment = MapFragment.newInstance();
        mMapFragment.getMapAsync(this);
        getFragmentManager().beginTransaction()
            .add(R.id.frame, mMapFragment, "TAG")
            .commit();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0))
                .snippet("Date " + new Date())
                .title("Start")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        /* ------------------------ CODICE PER INSERIRE LA MIA POSIZIONE -----------------------------
        -------------------------- TRACKER TROVATO IN RETE ------------------------------------
         */

        GPSTracker tracker = new GPSTracker(getApplicationContext());
        if (tracker.canGetLocation()) {

            Location locationGPS = tracker.getLocation();
            if(locationGPS != null){
                longitude = locationGPS.getLongitude();
                latitude = locationGPS.getLatitude();
                googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude))
                        .snippet("Date " + new Date())
                        .title("My Position")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
                Log.d("Location", "Location not null");
            }else
                Log.d("Location", "Location null");
        }else{
            tracker.showSettingsAlert();
        }
    }
}

