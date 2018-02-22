package com.dota.pearl18.pearlapp2018.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.api.ApiClient;
import com.dota.pearl18.pearlapp2018.api.ArticleDetails;
import com.dota.pearl18.pearlapp2018.api.ArticlesInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ArticlesFragment extends Fragment
{
    private RecyclerView recyclerView;
    private ArticlesAdapter adapter;
    private List<ArticleDetails> list = new ArrayList<>();
    private List<ArticleDetails> page_list = new ArrayList<>();
    private  String TAG = "Articles Fragment";

    public static ArticlesFragment newInstance(int page)
    {
        return new ArticlesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_articles,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.articles_recyclerview);
        adapter = new ArticlesAdapter(page_list,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        ArticlesInterface apiService = ApiClient.getClient().create(ArticlesInterface.class);
        Call<ArrayList<ArticleDetails>> call = apiService.getArticlesList();
        call.enqueue(new Callback<ArrayList<ArticleDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<ArticleDetails>> call, Response<ArrayList<ArticleDetails>> response) {
                list = response.body();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<ArticleDetails>> call, Throwable t) {
                Log.e(TAG,"Error in Connectivity");
            }
        });
    }
}
