package com.dota.pearl18.pearlapp2018.api;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ashwik on 20-02-2018.
 */

public class EventDetails extends RealmObject {

    @SerializedName("_id")
    String eventid ;

    @SerializedName("name")
    String eventname;

    @SerializedName("startTime")
    String starttime;

    @SerializedName("endTime")
    String endtime;

    @SerializedName("venue")
    String eventDescription ;

    String eventdate;


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

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }
}
