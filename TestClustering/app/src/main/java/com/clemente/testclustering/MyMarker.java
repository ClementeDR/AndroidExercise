package com.clemente.testclustering;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by clemente on 17/06/16.
 */
public class MyMarker implements ClusterItem {
    private LatLng position;
    private String title;

    public MyMarker(double latitute, double longitude){
        position = new LatLng(latitute, longitude);
    }

    public MyMarker(LatLng latLng){
        position = latLng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public LatLng getPosition() {
        return position;
    }
}
