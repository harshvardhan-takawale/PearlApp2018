package com.dota.pearl18.pearlapp2018.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dota.pearl18.pearlapp2018.adapters.CreditsAdapter;
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
        details.add(new DeveloperLayoutDetails(null, getAndroidDevs2()));
        details.add(new DeveloperLayoutDetails("Web Developers", getWebDevs()));
        details.add(new DeveloperLayoutDetails("Designers", getDesigners()));

        return details;
    }

    private ArrayList<DeveloperDetails> getAndroidDevs(){
        ArrayList<DeveloperDetails> details = new ArrayList<>();
        details.add(new DeveloperDetails("C Shri Akhil", R.drawable.dev_akhil, new String[] {"https://www.facebook.com/c.akhil.shri","https://github.com/TheGamer007/","https://www.linkedin.com/in/shriakhilc/",null}));
        details.add(new DeveloperDetails("Harshvardhan Takawale", R.drawable.dev_harshvardhan, new String[] { "https://www.facebook.com/harshvardhan.takawale","https://github.com/harshvardhan-takawale","https://www.linkedin.com/in/harshvardhan-takawale-9b5125142/",null}));
        details.add(new DeveloperDetails("Ashwik Reddy", R.drawable.dev_ashwik, new String[] {"https://www.facebook.com/ashwik.aileni","https://github.com/Ashwik",null,null}));
        return details;
    }

    private ArrayList<DeveloperDetails> getAndroidDevs2(){
        ArrayList<DeveloperDetails> details = new ArrayList<>();
        details.add(new DeveloperDetails("Kartheek Akella", R.drawable.dev_kartheek, new String[]{"https://www.facebook.com/kartheek.asvs","https://github.com/ASVS-Kartheek",null,null} ));
        details.add(new DeveloperDetails("Prateek Agarwal", R.drawable.dev_prateek, new String[]{"https://www.facebook.com/prateek.agarwal.94801","https://github.com/prat-bphc52",null,null}));
        details.add(new DeveloperDetails("Vineeth P", R.drawable.dev_vineeth, new String[]{"https://www.facebook.com/vineeth.vinny.007","https://github.com/vineeth07",null,null}));
        return details;
    }

    private ArrayList<DeveloperDetails> getWebDevs(){
        ArrayList<DeveloperDetails> details = new ArrayList<>();

        details.add(new DeveloperDetails("Rohitt Vashishtha", R.drawable.dev_rohitt, new String[] {"https://www.facebook.com/VagrantRohitt","https://github.com/aero31aero",null,null}));
        details.add(new DeveloperDetails("Sohail Rajdev", R.drawable.dev_sohail, new String[]{"https://www.facebook.com/srajdev97","https://github.com/sohailrajdev97",null, null}));
        return details;
    }

    private ArrayList<DeveloperDetails> getDesigners(){
        ArrayList<DeveloperDetails> details = new ArrayList<>();

        details.add(new DeveloperDetails("Suraj Thotakura", R.drawable.des_suraj, new String[]{"https://www.facebook.com/satya.suraj.7", null, null, "https://www.behance.net/surajsatyaa29d"}));
        details.add(new DeveloperDetails("Govind Savio", R.drawable.profile_icon, new String[]{"https://www.facebook.com/savio.sunny.79", null, null, null}));
        details.add(new DeveloperDetails("Abishek Yadav", R.drawable.des_yadav, new String[]{"https://www.facebook.com/profile.php?id=100000513640766",null,null,null}));
        return details;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
