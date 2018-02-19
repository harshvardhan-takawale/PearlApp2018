package com.dota.pearl18.pearlapp2018.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dota.pearl18.pearlapp2018.R;

public class SponsorsActivity extends AppCompatActivity {

    RecyclerView mSponsorsRecycler;
    SponsorsAdapter mSponsorsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        mSponsorsRecycler = findViewById(R.id.sponsors_recycler);
        mSponsorsAdapter = new SponsorsAdapter(this);
        mSponsorsRecycler.setLayoutManager(new LinearLayoutManager(this));
        mSponsorsRecycler.setAdapter(mSponsorsAdapter);
    }
}
