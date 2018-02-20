package com.dota.pearl18.pearlapp2018.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ashwik on 20-02-2018.
 */

public class EventDetails {

    @SerializedName("_id")
    String eventid ;

    @SerializedName("name")
    String eventname;

    @SerializedName("startTime")
    String starttime;

    @SerializedName("endTime")
    String endtime;

    public EventDetails(String eventid, String eventname, String starttime, String endtime) {
        this.eventid = eventid;
        this.eventname = eventname;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
