package com.dota.pearl18.pearlapp2018.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;


import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.api.ApiClient;
import com.dota.pearl18.pearlapp2018.api.ArticleDetails;
import com.dota.pearl18.pearlapp2018.api.ArticlesInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDisplayActivity extends AppCompatActivity {

    private static final String TAG = ArticleDisplayActivity.class.getSimpleName();
    private String id;
    private ViewPager viewPager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_display);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        id = getIntent().getStringExtra("_id");

        viewPager = findViewById(R.id.articles_viewpager);
        adapter = new ArticleDisplayActivity.viewpageradapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    public  class viewpageradapter extends FragmentStatePagerAdapter {

        public viewpageradapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return ArticlesFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Day"+String.valueOf(position+1) ;
        }
    }
}
