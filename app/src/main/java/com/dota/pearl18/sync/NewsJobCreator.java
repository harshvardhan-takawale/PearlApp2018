package com.dota.pearl18.sync;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by Vineeth on 3/20/2018.
 */

public class NewsJobCreator implements JobCreator {
    @Override
    public Job create(String tag) {
        switch (tag){
            case NewsJob.TAG:
                return new NewsJob();
            default:
                return null;
        }
    }
}
