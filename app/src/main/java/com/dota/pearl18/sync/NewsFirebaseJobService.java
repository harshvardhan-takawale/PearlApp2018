package com.dota.pearl18.sync;

import android.content.Context;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;


/**
 * Created by Vineeth on 3/12/2018.
 */

public class NewsFirebaseJobService extends JobService{
    private AsyncTask<Void, Void, Void> mCheckNewsTask;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        mCheckNewsTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Context context = getApplicationContext();
                NewsSyncTask.syncNews(context);
                jobFinished(jobParameters, false);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                jobFinished(jobParameters, false);
            }

        };

        mCheckNewsTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if(mCheckNewsTask != null){
            mCheckNewsTask.cancel(true);
        }
        return true;
    }
}