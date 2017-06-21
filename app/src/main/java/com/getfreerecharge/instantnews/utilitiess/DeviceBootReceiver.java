package com.getfreerecharge.instantnews.utilitiess;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by amit on 3/7/2017.
 */

public class DeviceBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            /* Setting the alarm here */
            Intent alarmIntent = new Intent(context, NotifyMe.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            int interval = 8000;
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);

           // Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();

            Intent alarmIntentone = new Intent(context, NotifyMe.class);
            PendingIntent pendingIntentone = PendingIntent.getBroadcast(context, 0, alarmIntentone, 0);

            AlarmManager managerone = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            int intervalone = 8000;
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), intervalone, pendingIntent);

            Intent alarmIntenttwo = new Intent(context, NotifyMe.class);
            PendingIntent pendingIntenttwo = PendingIntent.getBroadcast(context, 0, alarmIntenttwo, 0);

            AlarmManager managertwo = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            int intervaltwo = 8000;
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), intervaltwo, pendingIntent);

            Intent alarmIntentthree = new Intent(context, NotifyMe.class);
            PendingIntent pendingIntentthree = PendingIntent.getBroadcast(context, 0, alarmIntentthree, 0);

            AlarmManager managerthree = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            int intervalthree = 8000;
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), intervalthree, pendingIntent);
        }
    }

}
