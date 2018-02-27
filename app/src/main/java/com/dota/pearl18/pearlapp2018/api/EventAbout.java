package com.dota.pearl18.pearlapp2018.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pratd on 27-02-2018.
 */

public class EventAbout {
    @SerializedName("_id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("body")
    String body;
    @SerializedName("about")
    String about;
    @SerializedName("prize")
    String prize;
    @SerializedName("price")
    String price;
    @SerializedName("teamSize")
    String teamSize;
    @SerializedName("thumbnail")
    String thumbnail;
    public void setName(String name) {this.name = name;}
    public void setId(String id) {this.id = id;}
    public void setBody(String body) {this.body = body;}
    public void setAbout(String about) {this.about = about;}
    public void setPrice(String price) {this.price = price;}
    public void setPrize(String prize) {this.prize = prize;}
    public void setTeamSize(String teamSize) {this.teamSize = teamSize;}
    public void setThumbnail(String thumbnail) {this.thumbnail = thumbnail;}

    public String getName() {return name;}
    public String getBody() {return body;}
    public String getId() {return id;}
    public String getAbout() {return about;}
    public String getPrice() {return price;}
    public String getPrize() {return prize;}
    public String getTeamSize() {return teamSize;}
    public String getThumbnail() {return thumbnail;}
}
