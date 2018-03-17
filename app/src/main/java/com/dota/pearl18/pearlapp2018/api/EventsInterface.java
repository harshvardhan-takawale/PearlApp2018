package com.dota.pearl18.pearlapp2018.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ashwik on 20-02-2018.
 */

public interface EventsInterface {

    @GET("events")
    Call<ArrayList<EventDetails>> getEvents();

    @GET("events?fields=name,startTime,endTime,tagline,venue")
    Call<ArrayList<EventDetails>> getEventSchedule();

}
