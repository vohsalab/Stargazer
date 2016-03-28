package com.firstsputnik.stargazer.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;


/**
 * Implementation of App Widget functionality.
 */
public class ISSAppWidget extends AppWidgetProvider {


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        context.startService(new Intent(context, ISSWidgetIntentService.class));
    }


}

