package com.example.clemente.prova;


import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;


public class MainActivity extends Activity{

    private static final String GMAPSFRAG = "googlemapsfragment";
    private FragmentManager fragmentManager = getFragmentManager();
    private GoogleFragment googleFragment;
    private double latitude;
    private double longitude;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("latitude", latitude);
        outState.putDouble("longitude", longitude);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState != null) {
            latitude = savedInstanceState.getDouble("latitude");
            longitude = savedInstanceState.getDouble("longitude");
        }

        googleFragment = GoogleFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, googleFragment
                        , GMAPSFRAG)
                .commit();



    }


}

