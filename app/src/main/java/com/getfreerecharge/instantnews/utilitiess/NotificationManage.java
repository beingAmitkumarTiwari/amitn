package com.getfreerecharge.instantnews.utilitiess;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.getfreerecharge.instantnews.R;
import com.getfreerecharge.instantnews.activities.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by amit on 3/6/2017.
 */

public class NotificationManage extends Service {
    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Calendar c = Calendar.getInstance();
       // System.out.println("Current time =&gt; "+c.get());

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:a");
        String formattedDate = df.format(c.getTime());
// Now formattedDate have current date/time
        //Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();
        //int hours = new Time(System.currentTimeMillis()).getHours();
        int hr=c.get(Calendar.HOUR);

        int minut=c.get(Calendar.MINUTE);

        System.out.println("ata" +minut);

        if (minut==01) {
            gamingNewsNotify();
        }
        return START_STICKY;
    }

    private void gamingNewsNotify() {
        int notifyID = 1;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.gaming)
//                .setColor(getResources().getColor(R.color.notifyone))
                .setContentTitle("Instant News")
                .setContentText("See the latest news about gaming industry!");
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notifyID, mBuilder.build());

    }
}
