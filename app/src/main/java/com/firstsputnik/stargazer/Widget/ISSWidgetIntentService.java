package com.firstsputnik.stargazer.Widget;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.RemoteViews;

import com.firstsputnik.stargazer.Provider.meet.MeetColumns;
import com.firstsputnik.stargazer.Provider.meet.MeetCursor;
import com.firstsputnik.stargazer.R;
import com.firstsputnik.stargazer.View.MainActivity;

import java.util.Date;

/**
 * Created by ibalashov on 3/27/2016.
 */
public class ISSWidgetIntentService extends IntentService {


    public ISSWidgetIntentService() {
        super("ISSWidgetIntentService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, startId, startId);
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        return START_STICKY;
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        String nextMeet = "";

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,
                ISSAppWidget.class));

        Cursor c = getContentResolver().query(MeetColumns.CONTENT_URI, null, null, null, null);
        if (c!=null) {
            MeetCursor mc = new MeetCursor(c);
            mc.moveToFirst();
            nextMeet = (new Date(mc.getDatetime() * 1000)).toString();
            mc.close();
            c.close();
        }

        for (int appWidgetId : appWidgetIds) {
            int layoutId = R.layout.issapp_widget;
            RemoteViews views = new RemoteViews(getPackageName(), layoutId);
            if (nextMeet.equals("")) {
                views.setTextViewText(R.id.appwidget_text, "No ISS data");
            }
            else  {
                views.setTextViewText(R.id.appwidget_text, "Next ISS flyover is on:\n" + nextMeet);
            }
            Intent launchIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }




    }
}
