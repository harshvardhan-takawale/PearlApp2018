package com.dota.pearl18.sync;

import android.content.Context;
import android.support.annotation.NonNull;

import com.evernote.android.job.JobCreator;
import com.evernote.android.job.JobManager;

/**
 * Created by Vineeth on 3/20/2018.
 */

public class NewsReceiver extends JobCreator.AddJobCreatorReceiver {
    @Override
    protected void addJobCreator(@NonNull Context context, @NonNull JobManager manager) {
        manager.addJobCreator(new NewsJobCreator());
    }
}
