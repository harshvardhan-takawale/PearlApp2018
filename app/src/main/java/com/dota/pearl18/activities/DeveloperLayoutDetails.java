package com.dota.pearl18.activities;

import java.util.ArrayList;

/**
 * Created by Vineeth on 2/14/2018.
 */

public class DeveloperLayoutDetails {

    String title;
    ArrayList<DeveloperDetails> devs;

    public DeveloperLayoutDetails(String title, ArrayList<DeveloperDetails> devs) {
        this.title = title;
        this.devs = devs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<DeveloperDetails> getDevs() {
        return devs;
    }

    public void setDevs(ArrayList<DeveloperDetails> devs) {
        this.devs = devs;
    }

}
