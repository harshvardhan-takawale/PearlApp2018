package com.dota.pearl18.pearlapp2018.activities;

import com.dota.pearl18.pearlapp2018.R;

/**
 * Created by Vineeth on 2/13/2018.
 */

public class Contact {
    private int mImage;
    private String mName, mDesignation, mMobile;

    public Contact(String name, String designation){
        mName = name;
        mDesignation = designation;
    }

    public String getName() {
        return mName;
    }

    public String getDesignation() {
        return mDesignation;
    }
}
