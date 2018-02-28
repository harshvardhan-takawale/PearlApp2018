package com.dota.pearl18.pearlapp2018.activities;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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

        Button mAboutBtn = findViewById(R.id.guide_about_btn);
        mAboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateText(view,"about");
            }
        });

        Button mReachBtn = findViewById(R.id.guide_reach_btn);
        mReachBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateText(view,"dir");
            }
        });

        // landRightOne and cloudRightOne are the initially visible layers.
        // They will then be scrolled off the screen toward the left, and the other two will scroll in.
        // This goes on till the third image perfectly fits the screen.
        // After which we switch back to the RightOne(s).
        final ImageView landRightOne = findViewById(R.id.land_right_one);
        final ImageView cloudRightOne = findViewById(R.id.cloud_right_one);

        final ImageView landLeftOne = findViewById(R.id.land_left_one);
        final ImageView cloudLeftOne = findViewById(R.id.cloud_left_one);

        final ImageView landRightTwo = findViewById(R.id.land_right_two);
        final ImageView cloudRightTwo = findViewById(R.id.cloud_right_two);

        final ImageView busFull = findViewById(R.id.layer_bus);

        final ValueAnimator landAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        landAnimator.setRepeatCount(ValueAnimator.INFINITE);
        landAnimator.setInterpolator(new LinearInterpolator());
        landAnimator.setDuration(10000L);
        landAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = landRightOne.getWidth();
                // determine how much to the left should the image be shifted.
                // when the animation terminates, backgroundOne should be at (-2 * width)
                final float translationX = width * (progress * 2.0f); // this is a positive value

                landRightOne.setTranslationX(0 - translationX);

                landLeftOne.setTranslationX(width - translationX);

                landRightTwo.setTranslationX(2 * width - translationX);
            }
        });

        final ValueAnimator cloudAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        cloudAnimator.setRepeatCount(ValueAnimator.INFINITE);
        cloudAnimator.setInterpolator(new LinearInterpolator());
        cloudAnimator.setDuration(30000L);
        cloudAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = cloudRightOne.getWidth();
                // determine how much to the left should the image be shifted.
                // when the animation terminates, backgroundOne should be at (-2 * width)
                final float translationX = width * (progress * 2.0f); // this is a positive value

                cloudRightOne.setTranslationX(0 - translationX);

                cloudLeftOne.setTranslationX(width - translationX);

                cloudRightTwo.setTranslationX(2 * width - translationX);
            }
        });

        final ValueAnimator busAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        busAnimator.setRepeatCount(ValueAnimator.INFINITE);
        busAnimator.setInterpolator(new LinearInterpolator());
        busAnimator.setDuration(1000L);
        busAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float displacement = 0.01f * busFull.getHeight(); // 0.5% of the total height
                if (progress < 0.5f) {
                    // move down for first half
                    busFull.setTranslationY(2.0f * progress * displacement);
                } else {
                    // move back up for second half
                    busFull.setTranslationY(2.0f * (1.0f - progress)* displacement);
                }
            }
        });

        landAnimator.start();
        cloudAnimator.start();
        busAnimator.start();
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

            String transitionName = getString(R.string.transition_string);

            View viewStart = findViewById(R.id.cardOne);

            ActivityOptionsCompat options =

                    ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                            viewStart,   // Starting view
                            transitionName    // The String
                    );
            ActivityCompat.startActivity(this, intent, options.toBundle());
        }

    }

    public void animateText(View view, String text) {
        Intent aboutIntent = new Intent(GuideActivity.this, TextDisplayActivity.class);
        aboutIntent.putExtra("text",text);
        startActivity(aboutIntent);
    }
}
