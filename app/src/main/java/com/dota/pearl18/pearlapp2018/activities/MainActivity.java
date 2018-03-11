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
    private Button creditsButton;
    private Button RegisterButton,Schedule,Events, mGuideBtn, newsButton;


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

        ((Button) findViewById(R.id.qrscan)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, QRScannerActivity.class));
            }
        });

        /*  LANDING ANIMATION CODE
        // backgroundOne is the initially visible image. It will then be scrolled off the screen
        // toward the left, and the other two will scroll in. This goes on till the third image
        // i.e. backgroundThree perfectly fits the screen. After which we switch to backgroundOne.
        final ImageView backgroundOne = findViewById(R.id.land_left_one);
        final ImageView backgroundTwo = findViewById(R.id.land_left_one);
        final ImageView backgroundThree = findViewById(R.id.land_right_two);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                // determine how much to the left should the image be shifted.
                // when the animation terminates, backgroundOne should be at (-2 * width)
                final float translationX = width * (progress * 2.0f); // this is a positive value
                backgroundOne.setTranslationX(0 - translationX);
                backgroundTwo.setTranslationX(width - translationX);
                backgroundThree.setTranslationX(2 * width - translationX);
            }
        });
        animator.start();
        */

    }

}
