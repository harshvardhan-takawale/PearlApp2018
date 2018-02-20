package com.dota.pearl18.pearlapp2018.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ashwik on 20-02-2018.
 */

public interface EventsInterface {

    @GET("event")
    Call<ArrayList<EventDetails>> getEvents();

    @GET("event/schedule")
    Call<ArrayList<EventDetails>> getEventSchedule();
}
