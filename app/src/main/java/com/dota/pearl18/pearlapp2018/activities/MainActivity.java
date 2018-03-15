package com.dota.pearl18.pearlapp2018.activities;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dota.pearl18.pearlapp2018.adapters.LandingAdapter;
import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.sync.NewsSyncUtils;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    private Button contactUsButton;
    private Button creditsButton;
    private Button RegisterButton,Schedule,Events, mGuideBtn, newsButton;
    private ArrayList<LandingButtonDetails> buttonList;
    private LandingAdapter buttonAdapter;
    private DiscreteScrollView discreteScrollView;
    private InfiniteScrollAdapter infiniteAdapter;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = Uri.parse("https://www.bits-pearl.org/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        buttonList = new ArrayList<LandingButtonDetails>();

        buttonList.add(new LandingButtonDetails("Events",
            new Intent(MainActivity.this, EventsActivity.class),
            this));

        buttonList.add(new LandingButtonDetails("News",
            new Intent(getApplicationContext(),NewsActivity.class),
            this));

        buttonList.add(new LandingButtonDetails("Pro Shows",
            new Intent(MainActivity.this, ProshowActivity.class),
            this));

        buttonList.add(new LandingButtonDetails("Schedule",
            new Intent(MainActivity.this, ScheduleActivity.class),
            this));

        buttonList.add(new LandingButtonDetails("Guide",
            new Intent(getApplicationContext(),GuideActivity.class),
            this));

        buttonList.add(new LandingButtonDetails("App Credits",
            new Intent(MainActivity.this, CreditsActivity.class),
            this));

        buttonList.add(new LandingButtonDetails("Contact Us",
            new Intent(MainActivity.this, ContactsActivity.class),
            this));

        buttonList.add(new LandingButtonDetails("Register",
            intent,
            this));

        buttonList.add(new LandingButtonDetails("QR Scanner",
            new Intent(MainActivity.this, QRScannerActivity.class),
            this));

        buttonAdapter = new LandingAdapter(buttonList);
        infiniteAdapter = InfiniteScrollAdapter.wrap(buttonAdapter);

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

        NewsSyncUtils.initialize(this);
    }

    private void onItemChanged(int pos) {
        name = findViewById(R.id.Button_name);
        name.setText(buttonList.get(pos).getButtonName());
    }
}
