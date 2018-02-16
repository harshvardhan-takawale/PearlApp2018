package com.dota.pearl18.pearlapp2018.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dota.pearl18.pearlapp2018.eventsAdapter.InnerData;
import com.ramotion.garlandview.TailLayoutManager;
import com.ramotion.garlandview.TailRecyclerView;
import com.ramotion.garlandview.TailSnapHelper;
import com.ramotion.garlandview.header.HeaderTransformer;

import com.dota.pearl18.pearlapp2018.R;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity {

    List<List<InnerData>> outerData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        outerData = new ArrayList<>();
        for (int i=0;i<20;i++) {
            final List<InnerData> innerData = new ArrayList<>();
            for (int j=0;j<10; j++) {
                innerData.add(createInnerData());
            }
            outerData.add(innerData);
        }
    }
    public InnerData createInnerData()
    {
        return new InnerData("Dance","hello2","hello","http://",20000);
    }
}
