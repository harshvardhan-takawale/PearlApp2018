package com.dota.pearl18.pearlapp2018.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by pratd on 25-02-2018.
 */

public interface ClubInterface {
    @GET("bodies")
    Call<ArrayList<ClubDetails>> getClubList();

    @GET("events?fields=name,body,prize,teamSize,thumbnail,type")
    Call<ArrayList<EventAbout>> getEventDetails();

    @GET("events/{id}")
    Call<EventAbout> getEventListDetails(@Path("id") String id);
}
