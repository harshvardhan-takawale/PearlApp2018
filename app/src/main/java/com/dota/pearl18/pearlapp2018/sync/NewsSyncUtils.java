package com.dota.pearl18.pearlapp2018.sync;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vineeth on 3/12/2018.
 */

public class NewsSyncUtils {

    private static final int SYNC_INTERVAL_MINUTES = 30;
    private static final int SYNC_INTERVAL_MILLIS = (int) TimeUnit.MINUTES.toSeconds(SYNC_INTERVAL_MINUTES);
    private static final int SYNC_FLEXTIME_MILLIS = SYNC_INTERVAL_MILLIS/2;

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
                        SYNC_INTERVAL_MILLIS, SYNC_INTERVAL_MILLIS+SYNC_FLEXTIME_MILLIS))
                .setReplaceCurrent(true)
                .build();

        dispatcher.schedule(newsSyncJob);
    }

    synchronized public static void initialize(final Context context){
        if(sInitialized) return;
        sInitialized = true;
        scheduleFirebaseJobDispatcherSync(context);
    }

}
