package com.clemente.testclustering;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

/**
 * Created by clemente on 17/06/16.
 */

public class MyRendering  extends DefaultClusterRenderer<MyMarker> {

    private int count = 0;
    private Context context;
    private final IconGenerator mClusterIconGenerator;

    public MyRendering(Context context, GoogleMap map,
                       ClusterManager<MyMarker> clusterManager) {
        super(context, map, clusterManager);
        this.context = context;
        mClusterIconGenerator =  new IconGenerator(context);
    }


    protected void onBeforeClusterItemRendered(MyMarker item, MarkerOptions markerOptions) {
        markerOptions.title(item.getTitle());
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        super.onBeforeClusterItemRendered(item, markerOptions);
    }


    //SE SI VUOLE SETTARE L'ICONA BISOGNA AGGIUNGERE QUESTA ROBA
    @Override
    protected void onBeforeClusterRendered(Cluster<MyMarker> cluster, MarkerOptions markerOptions){
                //Per catturare l'icona
        final Drawable clusterIcon = context.getDrawable(R.mipmap.ic_launcher);

        //PER SETTARE IL COLORE
       // clusterIcon.setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_ATOP);

        //Per Inserire l'icona
        mClusterIconGenerator.setBackground(clusterIcon);

        //modify padding for one or two digit numbers
        if (cluster.getSize() < 10) {
            mClusterIconGenerator.setContentPadding(50, 65, 0, 0);
        }
        else if (cluster.getSize() >= 10 && cluster.getSize() < 100){
            mClusterIconGenerator.setContentPadding(37, 65, 0, 0);
        } else {
            mClusterIconGenerator.setContentPadding(20, 65, 0, 0);
        }

        Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(" " + cluster.getSize()));

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        markerOptions.title("Cluster " + count);
        count++;
    }
}