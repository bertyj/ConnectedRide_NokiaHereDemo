
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
                    ClusterLayer cl = new ClusterLayer();
                    GeoCoordinate location = new GeoCoordinate(49.196261, -123.004773);
                    GeoCoordinate startPoint = new GeoCoordinate(49.1966286, -123.0053635);
                    GeoCoordinate endPoint = new GeoCoordinate(49.1947289, -123.1762924);
                    map.setCenter(location, Animation.BOW);
                    MapMarker mmLocation = new MapMarker();
                    Image markerLocation = new Image();
                    try {
                        markerLocation.setImageResource(R.drawable.gasmarker);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mmLocation.setIcon(markerLocation);
                    mmLocation.setCoordinate(location);

                    MapMarker mmStartPoint = new MapMarker();
                    Image markerStartPoint = new Image();
                    try {
                        markerStartPoint.setImageResource(R.drawable.usermarker);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mmStartPoint.setIcon(markerStartPoint);
                    mmStartPoint.setCoordinate(startPoint);

                    MapMarker mmEndPoint = new MapMarker();
                    Image markerEndPoint = new Image();
                    try {
                        markerEndPoint.setImageResource(R.drawable.bikemarker);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mmEndPoint.setIcon(markerEndPoint);
                    mmEndPoint.setCoordinate(endPoint);

                    cl.addMarker(mmLocation);
                    cl.addMarker(mmStartPoint);
                    cl.addMarker(mmEndPoint);

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
