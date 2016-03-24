package com.firstsputnik.stargazer.Receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.firstsputnik.stargazer.R;

public class OneTimeAlarmReceiver extends BroadcastReceiver {
    public OneTimeAlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_iss)
                .setContentTitle("ISS will be over you in 15 mins!")
                .setContentText("Time to take a look at the sky!");

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, mBuilder.build());
    }
}
