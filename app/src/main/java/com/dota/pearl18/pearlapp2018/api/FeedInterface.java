package com.dota.pearl18.pearlapp2018.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Vineeth on 3/16/2018.
 */

public interface FeedInterface {
    @GET("feed")
    Call<FeedResponseDetails> getScoresfeed(@Query("page") int num, @Query("sort") String key, @Query("direction") String ord);
}
