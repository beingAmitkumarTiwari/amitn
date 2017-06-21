package com.getfreerecharge.instantnews.utilitiess;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.getfreerecharge.instantnews.R;
import com.getfreerecharge.instantnews.activities.MainActivity;

/**
 * Created by amit on 3/6/2017.
 */

public class NotifyMe extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // For our recurring task, we'll just display a message
       // Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
        int notifyID = 0;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.gaming)
//                .setColor(getResources().getColor(R.color.notifyone))
                .setContentTitle("Gaming Gossip")
                .setContentText("See the latest news about gaming industry!");
        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notifyID, mBuilder.build());

    }





}
