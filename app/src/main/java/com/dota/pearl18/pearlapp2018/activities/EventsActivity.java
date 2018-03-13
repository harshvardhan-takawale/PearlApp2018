package com.dota.pearl18.pearlapp2018.activities;

import android.animation.ValueAnimator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dota.pearl18.pearlapp2018.adapters.ClubAdapter;
import com.dota.pearl18.pearlapp2018.api.ApiClient;
import com.dota.pearl18.pearlapp2018.api.ClubDetails;
import com.dota.pearl18.pearlapp2018.api.ClubInterface;

import com.dota.pearl18.pearlapp2018.R;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsActivity extends AppCompatActivity {

    //List<List<InnerData>> outerData;
    ArrayList<ClubDetails> list;
    private ClubAdapter adapter;
    private DiscreteScrollView itemPicker;
    private InfiniteScrollAdapter infiniteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        itemPicker = findViewById(R.id.club_list);
        itemPicker.setOrientation(DSVOrientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
                onItemChanged(infiniteAdapter.getRealPosition(adapterPosition));
            }
        });

        ClubInterface apiService = ApiClient.getClient().create(ClubInterface.class);
        Call<ArrayList<ClubDetails>> call = apiService.getClubList();
        call.enqueue(new Callback<ArrayList<ClubDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<ClubDetails>> call, Response<ArrayList<ClubDetails>> response) {
                list=response.body();
                infiniteAdapter = InfiniteScrollAdapter.wrap(new ClubAdapter(list));
                itemPicker.setAdapter(infiniteAdapter);
                itemPicker.setItemTransitionTimeMillis(150);
                itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                        .setMinScale(0.8f)
                        .build());
                onItemChanged(0);
            }
            @Override
            public void onFailure(Call<ArrayList<ClubDetails>> call, Throwable t) {
                Log.e("Error:","Error in Connectivity");
                Toast.makeText(getApplicationContext(),"Error in connectivity",Toast.LENGTH_SHORT).show();
            }
        });

        //TODO: Remove garland view commented portion if not to be used
        /*outerData = new ArrayList<>();
        for (int i=0;i<5;i++) {
            final List<InnerData> innerData = new ArrayList<>();
            for (int j=0;j<5; j++) {
                innerData.add(createInnerData());
            }
            outerData.add(innerData);
        }
        initRecyclerView(outerData);*/

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

    }
    private void onItemChanged(int pos) {
        TextView name=findViewById(R.id.club_name);
        name.setText(list.get(pos).getName());
        TextView prize=findViewById(R.id.club_prize);
        //prize.setText("₹ "+list.get(pos).getPrize());
        prize.setText("₹ 10,000");
    }
    /*private void initRecyclerView(List<List<InnerData>> data) {

        final TailRecyclerView rv = findViewById(R.id.tail_recycler);
        ((TailLayoutManager)rv.getLayoutManager()).setPageTransformer(new HeaderTransformer());
        rv.setAdapter(new OuterAdapter(data));
        new TailSnapHelper().attachToRecyclerView(rv);
    }
    public InnerData createInnerData()
    {
        return new InnerData("Dance","hello2","hello","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuW7X6D7YJbn0rcswQwrb_x-Cfq30lsyrJQhE7kRaLWLUFwcSS",20000);
    }*/
}
