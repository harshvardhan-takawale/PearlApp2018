package com.dota.pearl18.sync;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vineeth on 3/20/2018.
 */

public class NewsJob extends Job{


    public static boolean mSynchronised;
    public static final String  TAG = "show_feed";
    @NonNull
    @Override
    protected Result onRunJob(Params params) {
        NewsSyncTask.syncNews(getContext());
        return Result.SUCCESS;
    }

    public static void schedulePeriodic(){
        int jobId = new JobRequest.Builder(NewsJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(5))
                .setUpdateCurrent(true)
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .build()
                .schedule();
        Log.d(TAG, String.valueOf(jobId));
    }
}
