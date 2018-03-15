package com.dota.pearl18.pearlapp2018.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vineeth on 3/15/2018.
 */

public class FeedDetails {
    @SerializedName("_id")
    String id;

    @SerializedName("sport")
    String sport;

    @SerializedName("text")
    String scorestext;

    @SerializedName("team1")
    String team1;

    @SerializedName("team2")
    String team2;

    @SerializedName("__v")
    String win;

    @SerializedName("createdAt")
    String createdtime;

    public FeedDetails(String id, String sport, String scorestext, String team1, String team2, String win, String createdtime) {
        this.id = id;
        this.sport = sport;
        this.scorestext = scorestext;
        this.team1 = team1;
        this.team2 = team2;
        this.win = win;
        this.createdtime = createdtime;
    }


    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getScorestext() {
        return scorestext;
    }

    public void setScorestext(String scorestext) {
        this.scorestext = scorestext;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }
}
