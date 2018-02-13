package com.dota.pearl18.pearlapp2018.activities;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;

import com.dota.pearl18.pearlapp2018.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = MapsActivity.class.getSimpleName();

    private SearchBox mSearchBox;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        mSearchBox = findViewById(R.id.searchbox);

        addMapLocations();

        mSearchBox.setLogoText("Lost somewhere?");
        mSearchBox.setDrawerLogo(R.mipmap.ic_launcher);
        mSearchBox.setLogoTextColor(Color.GRAY);

        mSearchBox.setSearchListener(new SearchBox.SearchListener() {
            @Override
            public void onSearchOpened() {
                mSearchBox.setHint("Type for suggestions...");
            }

            @Override
            public void onSearchCleared() {

            }

            @Override
            public void onSearchClosed() {
                //Log.i(TAG, "onSearchClosed");
            }

            @Override
            public void onSearchTermChanged(String s) {

            }

            @Override
            public void onSearch(String s) {
                goToLocation(s);
            }

            @Override
            public void onResultClick(SearchResult searchResult) {
                goToLocation(searchResult.title);
            }
        });
    }

    public void goToLocation(String title) {
        LatLng latLng = null;
        for (int i = 0; i < location_names.length; i++){
            String loc = location_names[i];
            if (title.equalsIgnoreCase(loc)) {
                latLng = new LatLng(latitudes[i], longitudes[i]);
            }
        }

        if (latLng != null) {
            CameraPosition cam = new CameraPosition.Builder()
                    .target(latLng)
                    .zoom(18)
                    .bearing(180)
                    .tilt(60)
                    .build();

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cam));
        } else {
            new StyleableToast.Builder(this)
                    .text(title + " not found")
                    .textColor(Color.WHITE)
                    .backgroundColor(Color.RED)
                    .show();
        }
    }

    private void addMapLocations() {
        for (String curr : location_names) {
            SearchResult option = new SearchResult(curr, ContextCompat.getDrawable(this, android.R.drawable.ic_menu_directions));
            mSearchBox.addSearchable(option);
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        IconGenerator factory = new IconGenerator(getApplicationContext());
        factory.setStyle(IconGenerator.STYLE_BLUE);
        mMap = googleMap;

        try {
            mMap.setMyLocationEnabled(true);
        } catch (SecurityException e) {
            // Location permission not given, or other runtime error occured. Do not enable the feature.
            // Note: Since we aren't letting them open this map without granting the permission, this block should never hit.
            //Log.i(TAG, "onMapReady: " + e.getMessage());
        }

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(
                this, R.raw.map_style);
        mMap.setMapStyle(style);

        for (int i = 0; i < location_names.length; i++) {

            Bitmap icon = factory.makeIcon(location_names[i]);
            LatLng location = new LatLng(latitudes[i], longitudes[i]);
            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(icon)).position(location));
        }

        CameraPosition cam = new CameraPosition.Builder()
                .target(new LatLng(latitudes[0], longitudes[0]))
                .zoom(18)
                .bearing(180)
                .tilt(60)
                .build();
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cam));
    }

    public static String[] location_names = {
            "Main Gate",
            "Bus Stop",
            "Cafeteria",
            "Auditorium",
            "SBI & ATM",
            "More Shopping mall",
            "Mess 1",
            "Mess 2",
            "Student Activity Center",
            "Cricket Ground",
            "Football Ground",
            "Volleyball Court",
            "Basketball Court",
            "Athletics Track",
            "Kabaddi Arena",
            "Tennis Court",
            "Hockey Ground"};

    public static double[] latitudes = {
            17.547152,
            17.547400,
            17.544982,
            17.545510,
            17.542241,
            17.542021,
            17.542428,
            17.544771,
            17.540799,
            17.539651,
            17.543518,
            17.543027,
            17.541337,
            17.554366,
            17.543386,
            17.541702,
            17.543819};

    public static double[] longitudes = {
            78.572481,
            78.572387,
            78.570834,
            78.570511,
            78.575974,
            78.576085,
            78.574010,
            78.575194,
            78.575273,
            78.577416,
            78.574854,
            78.575390,
            78.575361,
            78.545626,
            78.575632,
            78.575606,
            78.574045};
}
