package com.safe.drive.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.safe.drive.R;

import java.io.IOException;
import java.util.Locale;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMapClickListener, TextToSpeech.OnInitListener {
    private TextView mWeatherTextView;
    private GoogleMap mGoogleMap;
    private TextToSpeech mTts;

    private LatLng startLatLng;
    private int speedLimit;
    private final String ARG_SPEED_LIMIT = "SpeedLimit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseComponents();

        if (savedInstanceState != null) {
            if (savedInstanceState.getDouble("start_lat", Double.MIN_VALUE) != Double.MIN_VALUE) {
                this.startLatLng = new LatLng(savedInstanceState
                        .getDouble("start_lat"), savedInstanceState.getDouble("start_lng"));
            }
        }
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (this.startLatLng != null) {
            outState.putDouble("start_lat", this.startLatLng.latitude);
            outState.putDouble("start_lng", this.startLatLng.longitude);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, ProfileActivity.class));
            return true;
        } else if (id == R.id.action_show_weather) {
            if (!item.isChecked()) {
                mWeatherTextView.setVisibility(View.VISIBLE);
                mWeatherTextView.setText("Something");
                item.setChecked(true);
            } else {
                mWeatherTextView.setVisibility(View.GONE);
                item.setChecked(false);
            }
        } else if (id == R.id.action_clear_markers) {
            this.startLatLng = null;
            mGoogleMap.clear();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initialiseComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mWeatherTextView = (TextView) findViewById(R.id.weather_text_view);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mGoogleMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(new LatLng(54.98019000852768, -1.6135606169700623), 15));
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.setOnMapClickListener(this);
        mGoogleMap.setOnMyLocationButtonClickListener(this);

        if (this.startLatLng != null) {
            Marker marker = mGoogleMap.addMarker(new MarkerOptions().position(this.startLatLng).title("start"));
            marker.showInfoWindow();
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mGoogleMap.clear();
        this.startLatLng = latLng;

        String streetName = "Street name";
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        try {
            Address address = geocoder.getFromLocation(this.startLatLng.latitude, this.startLatLng.longitude, 2).get(0);
            streetName = address.getSubLocality();
            // Get speed limit from server
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.speedLimit = mSharedPreferences.getInt(ARG_SPEED_LIMIT, 40);
        Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                .position(latLng).title(streetName).snippet("Speed limit: " + speedLimit));
        marker.showInfoWindow();

        mTts = new TextToSpeech(this, this);
    }

    @Override
    public void onInit(int status) {
        int currentSpeed = mSharedPreferences.getInt(ARG_CURRENT_SPEED, 30);
        int speedLimit = mSharedPreferences.getInt(ARG_SPEED_LIMIT, 40);

        String message = "";
        if (currentSpeed > speedLimit) {
            message = "Mate, You overspeed.";
        }

        message +=
                "Current speed is " + currentSpeed +
                ", Speed limit is " + speedLimit;

        mTts.speak(message, TextToSpeech.QUEUE_ADD, null, null);
    }
}