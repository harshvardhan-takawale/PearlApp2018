package com.dota.pearl18.pearlapp2018.activities;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
