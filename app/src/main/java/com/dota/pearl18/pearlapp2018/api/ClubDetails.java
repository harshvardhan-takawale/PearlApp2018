package com.dota.pearl18.pearlapp2018.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pratd on 25-02-2018.
 */

public class ClubDetails {
    @SerializedName("_id")
    String id;
    @SerializedName("name")
    String name;

    public String getId() {return id;}
    public String getName() {return name;}
    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
}
