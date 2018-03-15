package com.dota.pearl18.pearlapp2018.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.adapters.ArticlesAdapter;
import com.dota.pearl18.pearlapp2018.api.ApiClient;
import com.dota.pearl18.pearlapp2018.api.ArticleDetails;
import com.dota.pearl18.pearlapp2018.api.ArticlesInterface;
import com.dota.pearl18.pearlapp2018.api.TestApiClient;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesActivity extends AppCompatActivity {

    private SwipeRefreshLayout mRefresh;
    private RecyclerView mRecycler;
    private ArticlesAdapter mAdapter;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mRefresh = findViewById(R.id.articles_swiperefresh);
        mRecycler = findViewById(R.id.articles_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        emptyView = findViewById(R.id.articles_empty);

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshArticlesList();
            }
        });

        refreshArticlesList();
    }

    private void refreshArticlesList() {
        mRefresh.setRefreshing(true);
        //TODO: Change this to ApiClient
        ArticlesInterface articles = TestApiClient.getClient().create(ArticlesInterface.class);
        Call<ArrayList<ArticleDetails>> call = articles.getArticlesList();

        call.enqueue(new Callback<ArrayList<ArticleDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<ArticleDetails>> call, Response<ArrayList<ArticleDetails>> response) {
                //Log.i("Articles", "onResponse: " + call.request().url());
                mRefresh.setRefreshing(false);
                ArrayList<ArticleDetails> articles_list = response.body();

                if (articles_list == null || articles_list.size() == 0) {
                    mRecycler.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    emptyView.setVisibility(View.GONE);
                    mRecycler.setVisibility(View.VISIBLE);


                    mAdapter = new ArticlesAdapter(ArticlesActivity.this, articles_list);
                    mRecycler.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ArticleDetails>> call, Throwable t) {
                mRefresh.setRefreshing(false);
                //Log.i("ArticlesActivity", "onFailure: " + call.request().url());
                mRecycler.setVisibility(View.GONE);
                emptyView.setText("Network not available. Retry later.");
                emptyView.setVisibility(View.VISIBLE);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
