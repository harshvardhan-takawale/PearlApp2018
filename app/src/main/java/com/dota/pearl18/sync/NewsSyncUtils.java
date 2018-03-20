package com.dota.pearl18.sync;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dota.pearl18.api.ApiClient;
import com.dota.pearl18.api.FeedDetails;
import com.dota.pearl18.api.FeedInterface;
import com.dota.pearl18.api.FeedResponseDetails;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vineeth on 3/12/2018.
 */

public class NewsSyncUtils {

    public static final String TAG = "NewsSyncUtils";

    private static final int SYNC_INTERVAL_MINUTES = 5;
    private static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.MINUTES.toSeconds(SYNC_INTERVAL_MINUTES);
    private static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS / 2;

    private static boolean sInitialized;

    private static final String NEWS_TAG = "news-tag";

    static void scheduleFirebaseJobDispatcherSync(Context context){

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job newsSyncJob = dispatcher.newJobBuilder()
                .setService(NewsFirebaseJobService.class)
                .setTag(NEWS_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(
                        SYNC_INTERVAL_SECONDS, SYNC_INTERVAL_SECONDS+SYNC_FLEXTIME_SECONDS))
                .setReplaceCurrent(false)
                .build();

        int result = dispatcher.schedule(newsSyncJob);
        if(result == FirebaseJobDispatcher.SCHEDULE_RESULT_SUCCESS){
            Log.d(TAG, "Job Successfully scheduled");
        }else {
            Log.d(TAG, "Job not scheduled");
        }
    }

    synchronized public static void initialize(final Context context){
        if(sInitialized) return;
        sInitialized = true;
        scheduleFirebaseJobDispatcherSync(context);

    }


}
