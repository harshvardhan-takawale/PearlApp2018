package com.dota.pearl18.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by pratd on 25-02-2018.
 */

public interface ClubInterface {
    @GET("bodies")
    Call<ArrayList<ClubDetails>> getClubList();

    @GET("events?fields=name,body,prize,teamSize,thumbnail,type,tagline,venue")
    Call<ArrayList<EventAbout>> getEventDetails();

    @GET("events/{id}")
    Call<EventAbout> getEventListDetails(@Path("id") String id);
}
