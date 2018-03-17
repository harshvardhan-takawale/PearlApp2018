package com.dota.pearl18.pearlapp2018.api;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by pratd on 25-02-2018.
 */

public class ClubDetails extends RealmObject{
    @SerializedName("_id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("img_res")
    int img_res;

    public String getId() {return id;}
    public String getName() {return name;}
    public int getImg_res(){return img_res;}
    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setImg_res(int img_res){this.img_res=img_res;}
}
