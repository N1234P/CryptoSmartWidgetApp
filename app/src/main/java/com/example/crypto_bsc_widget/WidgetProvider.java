package com.example.crypto_bsc_widget;

import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetManager;

import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.net.Uri;

import android.widget.RemoteViews;

import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


public class WidgetProvider extends AppWidgetProvider {
    public static final String ACTION_REFRESH = "actionRefresh";

    public static List<String> previous = new ArrayList<String>();
    public static List<ArrayList<String>> profiles = new ArrayList<ArrayList<String>>();



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for(int appWidgetId : appWidgetIds) {



            Intent serviceIntent = new Intent(context, WidgetService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

            Intent clickIntent = new Intent(context, WidgetProvider.class);
            clickIntent.setAction(ACTION_REFRESH);
            PendingIntent clickPendingIntent = PendingIntent.getBroadcast(context, 0, clickIntent, PendingIntent.FLAG_MUTABLE);


            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

            views.setRemoteAdapter(R.id.widget_stack_view, serviceIntent);
            views.setEmptyView(R.id.widget_stack_view, R.id.widget_empty_view);
            views.setPendingIntentTemplate(R.id.widget_stack_view, clickPendingIntent);




            appWidgetManager.updateAppWidget(appWidgetId,views);



        }


    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Toast.makeText(context, "onDeleted", Toast.LENGTH_SHORT).show();

    }

    public void onEnabled(Context context) {
        Toast.makeText(context, "onEnabled", Toast.LENGTH_SHORT).show();
    }

    public void onDisabled(Context context) {
        Toast.makeText(context, "onDisabled", Toast.LENGTH_SHORT).show();
    }



   public void onReceive(Context context, Intent intent) {

        if(ACTION_REFRESH.equals(intent.getAction())) {
            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

            AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_stack_view);
        }
       super.onReceive(context, intent);
   }



}

