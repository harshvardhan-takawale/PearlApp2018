package com.dota.pearl18.pearlapp2018.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dota.pearl18.pearlapp2018.api.ApiClient;
import com.dota.pearl18.pearlapp2018.api.ClubDetails;
import com.dota.pearl18.pearlapp2018.api.ClubInterface;
import com.dota.pearl18.pearlapp2018.eventsAdapter.InnerData;
import com.dota.pearl18.pearlapp2018.eventsAdapter.OuterAdapter;
import com.ramotion.garlandview.TailLayoutManager;
import com.ramotion.garlandview.TailRecyclerView;
import com.ramotion.garlandview.TailSnapHelper;
import com.ramotion.garlandview.header.HeaderTransformer;

import com.dota.pearl18.pearlapp2018.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsActivity extends AppCompatActivity {

    //List<List<InnerData>> outerData;
    ArrayList<ClubDetails> list;
    private ClubAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        final RecyclerView recyclerView=findViewById(R.id.club_recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ClubInterface apiService = ApiClient.getClient().create(ClubInterface.class);
        Call<ArrayList<ClubDetails>> call = apiService.getClubList();
        call.enqueue(new Callback<ArrayList<ClubDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<ClubDetails>> call, Response<ArrayList<ClubDetails>> response) {
                list=response.body();
                adapter=new ClubAdapter(list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Log.d("Bodies list",list.get(0).getName());
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
