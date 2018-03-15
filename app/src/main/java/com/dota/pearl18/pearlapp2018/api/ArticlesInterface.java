package com.dota.pearl18.pearlapp2018.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface ArticlesInterface {
    @GET("news")
    Call<ArrayList<ArticleDetails>> getArticlesList(/*@Query("fields") String fields*/);

    @GET("news/{id}")
    Call<ArticleDetails> getArticle(@Path("id") String id);
}
