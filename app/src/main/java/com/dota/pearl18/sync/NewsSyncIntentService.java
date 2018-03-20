package com.dota.pearl18.sync;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Vineeth on 3/20/2018.
 */

public class NewsSyncIntentService extends IntentService{
    public NewsSyncIntentService() {
        super("NewsSyncIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        NewsSyncTask.syncNews(this);
    }
}
