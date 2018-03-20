package com.dota.pearl18.sync;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
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
    public static final String NOTIFICATION_CHANNEL_ID = "News";

    public static void showNotification(Context context, FeedDetails details){

        if(details == null) return;

        String title = details.getSport();
        String content = details.getScorestext();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(false);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.drawable.pearl)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true);

        Intent articleIntent = new Intent(context, FeedActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addNextIntentWithParentStack(articleIntent);
        PendingIntent pendingIntent = taskStackBuilder
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        notificationManager.notify(NEWS_NOTIFICATION_ID, builder.build());
    }
}
