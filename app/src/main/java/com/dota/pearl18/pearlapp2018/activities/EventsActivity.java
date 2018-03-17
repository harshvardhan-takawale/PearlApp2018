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
import com.dota.pearl18.pearlapp2018.api.EventDetails;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsActivity extends AppCompatActivity {

    //List<List<InnerData>> outerData;
    ArrayList<ClubDetails> list = new ArrayList<>();
    ArrayList<ClubDetails> realmlist = new ArrayList<>();
    private ClubAdapter adapter;
    private DiscreteScrollView itemPicker;
    private InfiniteScrollAdapter infiniteAdapter;
    private Realm realm;
    private String TAG = EventsActivity.class.getSimpleName();
    private boolean isnetwork = false;
    private ArrayList<Integer> cardimages;

    final ValueAnimator busAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
    final ValueAnimator landAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
    final ValueAnimator cloudAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        cardimages = new ArrayList<Integer>();
        cardimages.add(R.drawable.ic_music);
        cardimages.add(R.drawable.ic_dance);
        cardimages.add(R.drawable.ic_drama);
        cardimages.add(R.drawable.ic_journal);
        cardimages.add(R.drawable.ic_english_lit);
        cardimages.add(R.drawable.ic_hindi_tarang);
        cardimages.add(R.drawable.ic_foreign_language);
        cardimages.add(R.drawable.ic_fine_arts);
        cardimages.add(R.drawable.ic_movie);
        cardimages.add(R.drawable.ic_photography);
        cardimages.add(R.drawable.ic_quiz);
        cardimages.add(R.drawable.ic_digital_art);
        cardimages.add(R.drawable.ic_vfx);
        cardimages.add(R.drawable.ic_other_events);

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
                for(int i=0;i<list.size();i++)
                {
                    addDatatoRealm(list.get(i));
                }
                isnetwork = true;
                getDatafromRealm(realm);
                setAdapter();
            }
            @Override
            public void onFailure(Call<ArrayList<ClubDetails>> call, Throwable t) {
                Log.e("Error:","Error in Connectivity");
                isnetwork = false;
                getDatafromRealm(realm);
                setAdapter();
            }
        });

        final ImageView landRightOne = findViewById(R.id.land_right_one);
        final ImageView cloudRightOne = findViewById(R.id.cloud_right_one);

        final ImageView landLeftOne = findViewById(R.id.land_left_one);
        final ImageView cloudLeftOne = findViewById(R.id.cloud_left_one);

        final ImageView landRightTwo = findViewById(R.id.land_right_two);
        final ImageView cloudRightTwo = findViewById(R.id.cloud_right_two);

        final ImageView busFull = findViewById(R.id.layer_bus);


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
        if(realmlist.size()!=0) {

        }
    }

    private void addDatatoRealm(ClubDetails clublist)
    {
        realm.beginTransaction();
        ClubDetails model = realm.where(ClubDetails.class).equalTo("id",clublist.getId()).findFirst();
        if(model==null)
        {
            ClubDetails club = realm.createObject(ClubDetails.class);
            club.setId(clublist.getId());
            if(clublist.getId().equals("5a83e757aefb42456b594b6d"))
            {
               club.setName("Headliners");
            }
            else {
                club.setName(clublist.getName());
            }
        }
        else
        {
            if(clublist.getId().equals("5a83e757aefb42456b594b6d"))
            {
                model.setName("Headliners");
            }
            else {
                model.setName(clublist.getName());
            }
        }
        realm.commitTransaction();
    }

    private void getDatafromRealm(Realm realm1)
    {
        if(realm1!=null)
        {
            realmlist= new ArrayList<>();
            RealmResults<ClubDetails> results = realm1.where(ClubDetails.class).findAll();

            if(results.size()==0)
            {
            Toast.makeText(this,"No Network",Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(isnetwork==false)
                {Toast.makeText(this,"No Network...Loading Offline Data",Toast.LENGTH_SHORT).show();}

                for(int i=0;i<results.size();i++)
                {
                    if(results.get(i).getId().equals("5a816324aefb42456b594b4f"))
                    {

                    }
                    else
                    {
                        realmlist.add(results.get(i));

                    }

                 }
            }
            Log.e(TAG,"realmlist:"+String.valueOf(realmlist.size()));
        }

    }

    private void setAdapter()
    {
        infiniteAdapter = InfiniteScrollAdapter.wrap(new ClubAdapter(realmlist, cardimages));
        itemPicker.setAdapter(infiniteAdapter);
        itemPicker.setItemTransitionTimeMillis(150);
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
        onItemChanged(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        landAnimator.pause();
        busAnimator.pause();
        cloudAnimator.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        landAnimator.resume();
        busAnimator.resume();
        cloudAnimator.resume();
    }
}
