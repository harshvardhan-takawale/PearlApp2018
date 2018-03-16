package com.dota.pearl18.pearlapp2018.activities;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.adapters.LandingAdapter;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    private ArrayList<LandingButtonDetails> buttonList;
    private LandingAdapter buttonAdapter;
    private DiscreteScrollView discreteScrollView;
    //private InfiniteScrollAdapter infiniteAdapter;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        buttonList = new ArrayList<LandingButtonDetails>();

        Intent articlesIntent = new Intent(NewsActivity.this,ArticlesActivity.class);
        Intent feedIntent = new Intent(NewsActivity.this,FeedActivity.class);

        buttonList.add(new LandingButtonDetails("Feed",
                feedIntent,this));

        buttonList.add(new LandingButtonDetails("Articles",
                articlesIntent,this));
        buttonAdapter = new LandingAdapter(buttonList);
        //infiniteAdapter = InfiniteScrollAdapter.wrap(buttonAdapter);

        discreteScrollView = findViewById(R.id.Button_list);
        discreteScrollView.setOrientation(DSVOrientation.HORIZONTAL);


        discreteScrollView.setAdapter(buttonAdapter);
        discreteScrollView.setItemTransitionTimeMillis(150);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
        discreteScrollView.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
                onItemChanged(adapterPosition);
            }
        });
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

    }

    private void onItemChanged(int pos) {
        name = findViewById(R.id.Button_name);
        name.setText(buttonList.get(pos).getButtonName());
    }

}
