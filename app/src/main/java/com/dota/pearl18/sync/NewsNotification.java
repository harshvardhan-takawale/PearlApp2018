package com.dota.pearl18.sync;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.dota.pearl18.R;
import com.dota.pearl18.activities.FeedActivity;
import com.dota.pearl18.api.FeedDetails;

/**
 * Created by Vineeth on 3/12/2018.
 */

public class NewsNotification {

    private static final int NEWS_NOTIFICATION_ID = 101;

    public static void showNotification(Context context, FeedDetails details){

        if(details == null) return;

        String title = details.getSport();
        String content = details.getScorestext();

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.pearl)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true);

        Intent articleIntent = new Intent(context, FeedActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addNextIntentWithParentStack(articleIntent);
        PendingIntent pendingIntent = taskStackBuilder
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        notificationBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NEWS_NOTIFICATION_ID, notificationBuilder.build());
    }
}
