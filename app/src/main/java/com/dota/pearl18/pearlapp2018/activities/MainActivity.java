package com.dota.pearl18.pearlapp2018.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.dota.pearl18.pearlapp2018.Adapters.LandingAdapter;
import com.dota.pearl18.pearlapp2018.R;
import com.google.firebase.FirebaseApp;
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
        FirebaseApp.initializeApp(this);
        Uri uri = Uri.parse("https://www.bits-pearl.org/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        buttonList = new ArrayList<LandingButtonDetails>();

        buttonList.add(new LandingButtonDetails("Events",
            new Intent(MainActivity.this, EventsActivity.class),
            this));

        buttonList.add(new LandingButtonDetails("News",
            new Intent(getApplicationContext(),ArticleDisplayActivity.class),
            this));

        buttonList.add(new LandingButtonDetails("Pro Shows",
            new Intent(MainActivity.this, ScheduleActivity.class),
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

    private void onItemChanged(int pos) {
        name = findViewById(R.id.Button_name);
        name.setText(buttonList.get(pos).getButtonName());
    }

}
