package com.clemente.testclustering;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, ClusterManager.OnClusterClickListener<MyMarker> {
    private GoogleMap googleMap;
    private SupportMapFragment mapFragment;
    private ClusterManager<MyMarker> mClusterManager;
    double minLat = -90;//-90
    double maxLat = 90;//90
    double minLon = -180;//-180
    double maxLon = 180;//180

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMapReady) {
        this.googleMap = googleMapReady;

        setUpClusterer();
    }

    private void setUpClusterer() {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 10));

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<MyMarker>(getApplicationContext(), googleMap);
        //per personalizzare i cluster
        mClusterManager.setRenderer(new MyRendering(getApplicationContext(),this.googleMap,mClusterManager));


        mClusterManager.setOnClusterClickListener(this);
        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        googleMap.setOnCameraChangeListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);


        // Add cluster items (markers) to the cluster manager.
        addItems();

    }

    private void addItems() {

        for (int i = 0; i < 200; i++) {
            MyMarker offsetItem = new MyMarker(new LatLng(Math.random() * (maxLat - minLat) + minLat, Math.random() * (maxLon - minLon) + minLon));
            offsetItem.setTitle("Marker" + (i + 1));
            mClusterManager.addItem(offsetItem);
            Log.d("Log", "Marker " + i);
        }
    }

    @Override
    public boolean onClusterClick(Cluster<MyMarker> cluster) {
        Log.d("Cluster","" + cluster.getSize());
        return false;
    }

}

