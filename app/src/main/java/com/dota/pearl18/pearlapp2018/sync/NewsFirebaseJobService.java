package com.dota.pearl18.pearlapp2018.sync;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.AsyncTask;


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
