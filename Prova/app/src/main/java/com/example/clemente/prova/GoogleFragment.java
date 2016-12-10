package com.example.clemente.prova;


import android.app.Activity;
import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class GoogleFragment extends Fragment implements OnMapReadyCallback, LocationSource.OnLocationChangedListener{

    private MapView mMapView;
    private GoogleMap google;

    private double latitude = 10;
    private double longitude = 50;
    private double latitude2 = -10;
    private double longitude2 = -50;
    private double latitudeLocation;
    private double longitudeLocation;
    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude2   , longitude2))
                .title("My Position")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        Toast.makeText(getActivity(), "MAP READY", Toast.LENGTH_LONG).show();
        google = googleMap;

    }

    @Override
    public void onLocationChanged(Location location) {
        google.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude()))
                .title("My Position")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
        .snippet("Date " + new Date()));

    }


    public interface IOsetMappe{
        double getMyLatitude();
        double getMyLongitude();
    }

    private IOsetMappe mListener = new IOsetMappe() {

        @Override
        public double getMyLatitude() {
            return 0;
        }

        @Override
        public double getMyLongitude() {
            return 0;
        }
    };
    public GoogleFragment() {
        // Required empty public constructor
    }

    public static GoogleFragment newInstance(String sess, double aLatitude, double aLongitude){
        GoogleFragment fragment = new GoogleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("session", sess);
        bundle.putDouble("latitude", aLatitude);
        bundle.putDouble("longitude", aLongitude);
        fragment.setArguments(bundle);
        return fragment;
    }


    public static GoogleFragment newInstance(){
        return new GoogleFragment();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(getActivity() instanceof IOsetMappe){
            mListener = (IOsetMappe)getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_google, container, false);
        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        google = mMapView.getMap();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(getActivity(), "Ora  " + new Date(), Toast.LENGTH_SHORT).show();

        google.addMarker(new MarkerOptions().position(new LatLng(latitude2, -100))
                .title("My Position")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        /* --------------PROVA LOCATION MANAGER --------------------------*/

        GPSTracker tracker = new GPSTracker(getActivity());
        if (tracker.canGetLocation()) {

            Location locationGPS = tracker.getLocation();
            if(locationGPS != null){

                longitudeLocation = locationGPS.getLongitude();
                latitudeLocation = locationGPS.getLatitude();
                google.addMarker(new MarkerOptions().position(new LatLng(latitudeLocation, longitudeLocation))
                .snippet("Date " + new Date())
                .title("My Position")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
                Log.d("Location", "Location not null");
            }else
                Log.d("Location", "Location null");
        }else{
            tracker.showSettingsAlert();
        }
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
