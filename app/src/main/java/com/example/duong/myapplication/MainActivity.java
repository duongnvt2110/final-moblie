package com.example.duong.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;
import 	android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;
import android.support.v4.content.ContextCompat;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.Manifest;
import android.provider.Settings;
import android.os.Build;
import com.example.duong.myapplication.LocationList;
import com.example.duong.myapplication.ReviewList;
import com.example.duong.myapplication.Token;



public class MainActivity extends AppCompatActivity  {

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected int MY_PERMISSION_ACCESS_COARSE_LOCATION = 1;
    protected Context context;
    protected Token token;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude,longitude;
    protected boolean gps_enabled,network_enabled;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Call View  Location
        final Button btnView = (Button) findViewById(R.id.btn_view);
        btnView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewLocationItem.class));
            }
        });

//        Add if to check permission.
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    MY_PERMISSION_ACCESS_COARSE_LOCATION);
        }

//    Define location
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new MyLocationListener();
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        double[] text= showCurrentLocation();
        final TextView txt = (TextView) findViewById(R.id.text);
        txt.setText("Location changed: Lat: " + text[0] + " Lng: "
                + text[1]);
    }

    protected double[] showCurrentLocation() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    MY_PERMISSION_ACCESS_COARSE_LOCATION);
        }

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            double[] geo = {
                    location.getLongitude(),
                    location.getLatitude()
            };
        return geo;
    }

    //    Class to show lat and lng
    private class MyLocationListener implements LocationListener {

        final TextView txt = (TextView) findViewById(R.id.text);
        @Override
        public void onLocationChanged(Location loc) {
//            txt.setText("Location changed: Lat: " + loc.getLatitude() + " Lng: "
//                    + loc.getLongitude());
//            Token token = new Token();
//            token.saveToken("Location changed:");
//            String tokenTest = token.getToken();
//            Toast.makeText(
//                    getBaseContext(),
//                    "Location changed: Lat: " + loc.getLatitude() + " Lng: "
//                            + loc.getLongitude(), Toast.LENGTH_SHORT).show();
//            String longitude = "Longitude: " + loc.getLongitude();
        }

        @Override
        public void onProviderDisabled(String provider) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }
}
