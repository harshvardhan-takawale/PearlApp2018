package com.dota.pearl18.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Vineeth on 3/16/2018.
 */

public interface FeedInterface {
    @GET("feed")
    Call<FeedResponseDetails> getFeed(@Query("page") int num, @Query("sort") String key, @Query("direction") String ord);
}
