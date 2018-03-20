package com.dota.pearl18.sync;

import android.app.Application;

import com.evernote.android.job.JobManager;

/**
 * Created by Vineeth on 3/20/2018.
 */

public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JobManager.create(this).addJobCreator(new NewsJobCreator());
    }
}
