package com.dota.pearl18.pearlapp2018.guide;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dota.pearl18.pearlapp2018.Articles.TextDisplayActivity;
import com.dota.pearl18.pearlapp2018.R;

import static android.support.v4.content.PermissionChecker.PERMISSION_DENIED;
import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class GuideActivity extends AppCompatActivity {

    private static int permCheck;
    public static final int REQUEST_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        CardView mAboutCard = findViewById(R.id.cardTwo);
        mAboutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateText(view,"about");
            }
        });


        CardView mReachCard = findViewById(R.id.cardThree);
        // mReachBtn = findViewById(R.id.guide_reach_btn);
        mReachCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateText(view,"dir");
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){

            case REQUEST_LOCATION:
                if(grantResults[0] == PERMISSION_GRANTED)
                    startActivity(new Intent(this,MapsActivity.class));
                else
                    Toast.makeText(this,"Please activate Location Services", Toast.LENGTH_LONG).show();
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void animateMaps(View view) {
        permCheck = ContextCompat.checkSelfPermission(GuideActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permCheck == PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(GuideActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Intent intent = new Intent(GuideActivity.this,MapsActivity.class);

            // Get the transition name from the string
            String transitionName = getString(R.string.transition_string);

            // Define the view that the animation will start from
            View viewStart = findViewById(R.id.cardOne);

            ActivityOptionsCompat options =

                    ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                            viewStart,   // Starting view
                            transitionName    // The String
                    );
            //Start the Intent
            ActivityCompat.startActivity(this, intent, options.toBundle());
        }

    }

    public void animateText(View view, String text) {
        Intent aboutIntent = new Intent(GuideActivity.this, TextDisplayActivity.class);
        aboutIntent.putExtra("text",text);
        startActivity(aboutIntent);
    }
}
