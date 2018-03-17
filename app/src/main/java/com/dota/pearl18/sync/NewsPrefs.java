package com.dota.pearl18.sync;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Vineeth on 3/12/2018.
 */

public class NewsPrefs {

    private static final String LAST_NEWS_ID = "news_id";

    public static void setLastNewsId(Context context, String id){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(LAST_NEWS_ID, id);
        editor.apply();
    }

    public static String getLastNewsId(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(LAST_NEWS_ID, "");
    }
}
