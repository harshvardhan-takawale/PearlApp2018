package com.dota.pearl18.pearlapp2018.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dota.pearl18.pearlapp2018.R;

import java.util.ArrayList;

public class CreditsActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private CreditsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        mRecyclerView = findViewById(R.id.credits_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CreditsActivity.this));

        mAdapter = new CreditsAdapter(CreditsActivity.this, getDevData());

        mRecyclerView.setAdapter(mAdapter);

    }

    private ArrayList<DeveloperLayoutDetails> getDevData() {
        ArrayList<DeveloperLayoutDetails> details = new ArrayList<>(3);
        details.add(new DeveloperLayoutDetails("Android Developers", getAndroidDevs()));
        details.add(new DeveloperLayoutDetails("Web Developers", getWebDevs()));
        details.add(new DeveloperLayoutDetails("Designers", getDesigners()));

        return details;
    }

    //TODO - Change the data
    private ArrayList<DeveloperDetails> getAndroidDevs(){
        ArrayList<DeveloperDetails> details = new ArrayList<>();

        for(int i = 1; i < 4 ; i++){
            details.add(new DeveloperDetails("Android dev " + i, new String[]{"","","",null}));
        }
        return details;
    }

    private ArrayList<DeveloperDetails> getWebDevs(){
        ArrayList<DeveloperDetails> details = new ArrayList<>();

        for(int i = 1; i < 4 ; i++){
            details.add(new DeveloperDetails("Web dev " + i, new String[]{"","","",null}));
        }
        return details;
    }

    private ArrayList<DeveloperDetails> getDesigners(){
        ArrayList<DeveloperDetails> details = new ArrayList<>();

        for(int i = 1; i <4 ; i++){
            details.add(new DeveloperDetails("Designer " + i, new String[]{"", null, null, ""}));
        }
        return details;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
