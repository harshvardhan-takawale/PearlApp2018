package com.dota.pearl18.pearlapp2018.Articles;

import android.content.Intent;

import com.dota.pearl18.pearlapp2018.activities.ContactsActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dota.pearl18.pearlapp2018.R;
<<<<<<< HEAD
import com.dota.pearl18.pearlapp2018.activities.CreditsActivity;
import com.dota.pearl18.pearlapp2018.activities.MapsActivity;
||||||| merged common ancestors
import com.dota.pearl18.pearlapp2018.activities.MapsActivity;
=======
import com.dota.pearl18.pearlapp2018.guide.GuideActivity;
>>>>>>> 046c0a10e72217fdcc9f08f243d0ffdd50c83ec6
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    private Button contactUsButton;
    private Button TreasureHuntButton;
    private Button creditsButton;

    Button mGuideBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactUsButton = findViewById(R.id.contact_us);
        contactUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ContactsActivity.class));
            }
        });

        mGuideBtn = findViewById(R.id.main_guide_btn);
        mGuideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),GuideActivity.class));
            }
        });

        creditsButton = findViewById(R.id.credits);
        creditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreditsActivity.class));
            }
        });

        TreasureHuntButton = findViewById(R.id.treasure);

        TreasureHuntButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlaceDetectionClient mPlaceDetectionClient;

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    mPlaceDetectionClient = Places.getPlaceDetectionClient(getApplicationContext(), null);
                    Task<PlaceLikelihoodBufferResponse> placeResult = mPlaceDetectionClient.getCurrentPlace(null);
                    placeResult.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
                        @Override
                        public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                            PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                            for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                                Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                                        placeLikelihood.getPlace().getName(),
                                        placeLikelihood.getLikelihood()));
                            }
                            likelyPlaces.release();
                        }
                    });

                    return;
                }
            }
        });




    }

}
