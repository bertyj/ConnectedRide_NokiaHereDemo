
package com.bmwchina.cdl.ConnectedRide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.here.android.mpa.cluster.ClusterLayer;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.Map.Animation;
import com.here.android.mpa.mapping.MapFragment;
import com.here.android.mpa.mapping.MapMarker;

import java.io.IOException;

public class MainActivity extends Activity {
    private Map map;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.init(new OnEngineInitListener() {

            @Override
            public void onEngineInitializationCompleted(Error error) {
                // TODO Auto-generated method stub
                if (error == Error.NONE) {
                    Toast.makeText(MainActivity.this, "Map Engine Initialization Success", Toast.LENGTH_LONG).show();
                    map = mapFragment.getMap();
                    GeoCoordinate location = new GeoCoordinate(49.196261, -123.004773);
                    map.setCenter(location, Animation.BOW);
                    MapMarker mm = new MapMarker();
                    Image markerImage = new Image();
                    try {
                        markerImage.setImageResource(R.drawable.marker);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mm.setIcon(markerImage);
                    mm.setCoordinate(location);
                    ClusterLayer cl = new ClusterLayer();
                    cl.addMarker(mm);
                    map.addClusterLayer(cl);
                    map.setZoomLevel((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2);
                    // map.setZoomLevel(map.getMinZoomLevel());
                }
                else {
                    Toast.makeText(MainActivity.this, "Map Engine Initialization Fail", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
