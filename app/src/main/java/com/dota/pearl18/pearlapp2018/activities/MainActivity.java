package com.dota.pearl18.pearlapp2018.activities;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.dota.pearl18.pearlapp2018.R;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    private Button contactUsButton;
    private Button TreasureHuntButton;
    private Button creditsButton;
    private Button RegisterButton,Schedule,Events, Sponsors, mGuideBtn, newsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

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

        newsButton = findViewById(R.id.main_news_btn);
        newsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ArticleDisplayActivity.class));
            }
        });

        creditsButton = findViewById(R.id.credits);
        creditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreditsActivity.class));
            }
        });

        RegisterButton = findViewById(R.id.register);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.bits-pearl.org/"); 
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Schedule = findViewById(R.id.schedule);
        Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScheduleActivity.class));
            }
        });

        Events=findViewById(R.id.display_events);
        Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EventsActivity.class));
            }
        });

        Sponsors = findViewById(R.id.sponsors);
        Sponsors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SponsorsActivity.class));
            }
        });

        TreasureHuntButton = findViewById(R.id.treasure);

        TreasureHuntButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, TreasureHuntActivity.class));

               /* PlaceDetectionClient mPlaceDetectionClient;

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
                }*/
            }
        });

        ((Button) findViewById(R.id.qrscan)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, QRScannerActivity.class));
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

}