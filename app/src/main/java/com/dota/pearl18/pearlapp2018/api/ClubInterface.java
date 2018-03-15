package com.dota.pearl18.pearlapp2018.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by pratd on 25-02-2018.
 */

public interface ClubInterface {
    @GET("bodies")
    Call<ArrayList<ClubDetails>> getClubList();

    @GET("events")
    Call<ArrayList<EventAbout>> getEventDetails();

    @GET("events?fields=about")
    Call<ArrayList<EventAbout>> getEventListDetails();
}
