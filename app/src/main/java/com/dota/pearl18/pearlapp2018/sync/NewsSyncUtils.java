package com.dota.pearl18.pearlapp2018.sync;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vineeth on 3/12/2018.
 */

public class NewsSyncUtils {

    private static final int SYNC_INTERVAL_MINUTES = 30;
    private static final long SYNC_INTERVAL_MILLIS = (int) TimeUnit.MINUTES.toMillis(SYNC_INTERVAL_MINUTES);
    private static final long SYNC_FLEXTIME_MILLIS = SYNC_INTERVAL_MILLIS/2;

    private static boolean sInitialized;

    private static final int NEWS_SYNC = 101;

    static void scheduleFirebaseJobDispatcherSync(Context context){

        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        jobScheduler.schedule(new JobInfo.Builder(NEWS_SYNC, new ComponentName(context, NewsFirebaseJobService.class))
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                        .setPeriodic(SYNC_INTERVAL_MILLIS)
                        .build());


    }

    synchronized public static void initialize(final Context context){
        if(sInitialized) return;
        sInitialized = true;
        scheduleFirebaseJobDispatcherSync(context);
    }

}
