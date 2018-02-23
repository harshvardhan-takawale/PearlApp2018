package com.dota.pearl18.pearlapp2018.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dota.pearl18.pearlapp2018.eventsAdapter.InnerData;
import com.dota.pearl18.pearlapp2018.eventsAdapter.OuterAdapter;
import com.ramotion.garlandview.TailLayoutManager;
import com.ramotion.garlandview.TailRecyclerView;
import com.ramotion.garlandview.TailSnapHelper;
import com.ramotion.garlandview.header.HeaderTransformer;

import com.dota.pearl18.pearlapp2018.R;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity {

    //List<List<InnerData>> outerData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
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
