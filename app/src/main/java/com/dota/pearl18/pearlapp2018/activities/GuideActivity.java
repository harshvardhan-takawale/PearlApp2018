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

        final ImageView backgroundOne = findViewById(R.id.background_one);
        final ImageView backgroundTwo = findViewById(R.id.background_two);
        final ImageView backgroundThree = findViewById(R.id.background_three);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * (progress * 2.0f);
                backgroundOne.setTranslationX(translationX);
                backgroundTwo.setTranslationX(translationX - width);
                backgroundThree.setTranslationX(translationX - 2 * width);
            }
        });
        animator.start();
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
