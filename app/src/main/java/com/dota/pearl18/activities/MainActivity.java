package com.dota.pearl18.activities;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.dota.pearl18.R;
import com.dota.pearl18.adapters.LandingAdapter;
import com.dota.pearl18.sync.NewsJob;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    private ArrayList<LandingButtonDetails> buttonList;
    private LandingAdapter buttonAdapter;
    private DiscreteScrollView discreteScrollView;
    private InfiniteScrollAdapter infiniteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView splash = findViewById(R.id.splash_img);
        discreteScrollView = findViewById(R.id.Button_list);


        discreteScrollView.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
                anim.setDuration(500);
                anim.setRepeatCount(0);
                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        splash.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                splash.startAnimation(anim);

                AlphaAnimation anim2 = new AlphaAnimation(0.0f, 1.0f);
                anim2.setDuration(500);
                anim2.setRepeatCount(0);
                anim2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        discreteScrollView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                discreteScrollView.startAnimation(anim2);
            }
        }, 1500);

        Uri uri = Uri.parse("https://www.townscript.com/e/pearl2018-240304");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        buttonList = new ArrayList<LandingButtonDetails>();

        buttonList.add(new LandingButtonDetails("Events",
                new Intent(MainActivity.this, EventsActivity.class),
                this,R.drawable.ic_events));

        buttonList.add(new LandingButtonDetails("News",
                new Intent(getApplicationContext(), NewsActivity.class),
                this,R.drawable.ic_news));

        buttonList.add(new LandingButtonDetails("Pro Shows",
                new Intent(MainActivity.this, ProshowActivity.class),
                this, R.drawable.ic_pro_shows));

        buttonList.add(new LandingButtonDetails("Guide",
                new Intent(getApplicationContext(), GuideActivity.class),
                this,R.drawable.ic_guid));

        buttonList.add(new LandingButtonDetails("Contact Us",
                new Intent(MainActivity.this, ContactsActivity.class),
                this, R.drawable.ic_contact_us));

        buttonList.add(new LandingButtonDetails("QR Scanner",
                new Intent(MainActivity.this, QRScannerActivity.class),
                this, R.drawable.ic_qr_scanner));

        buttonList.add(new LandingButtonDetails("Register",
                intent,
                this, R.drawable.ic_register));

        buttonList.add(new LandingButtonDetails("App Credits",
                new Intent(MainActivity.this, CreditsActivity.class),
                this, R.drawable.ic_app_credits));

        buttonList.add(new LandingButtonDetails("Schedule",
                new Intent(MainActivity.this, ScheduleActivity.class),
                this, R.drawable.ic_schedule));

        buttonAdapter = new LandingAdapter(buttonList, this);
        infiniteAdapter = InfiniteScrollAdapter.wrap(buttonAdapter);

        ImageView img = findViewById(R.id.iv_background);

        discreteScrollView = findViewById(R.id.Button_list);
        discreteScrollView.setOrientation(DSVOrientation.HORIZONTAL);
        discreteScrollView.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
                onItemChanged(infiniteAdapter.getRealPosition(adapterPosition));
            }
        });

        discreteScrollView.setAdapter(infiniteAdapter);
        discreteScrollView.setItemTransitionTimeMillis(150);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
        onItemChanged(0);

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

        ValueAnimator landAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
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

        ValueAnimator cloudAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
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

        ValueAnimator busAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        busAnimator.setRepeatCount(ValueAnimator.INFINITE);
        busAnimator.setInterpolator(new LinearInterpolator());
        busAnimator.setDuration(1000L);
        busAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float displacement = 0.004f * busFull.getHeight();
                if (progress < 0.5f) {
                    // move up for first half
                    busFull.setTranslationY(-2.0f * progress * displacement);
                } else {
                    // move back down for second half
                    busFull.setTranslationY(-2.0f * (1.0f - progress) * displacement);
                }
            }
        });

        landAnimator.start();
        cloudAnimator.start();
        busAnimator.start();

        NewsJob.schedulePeriodic();
    }

    private void onItemChanged(int pos) {

    }
}
