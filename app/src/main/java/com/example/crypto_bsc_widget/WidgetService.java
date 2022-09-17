package com.example.crypto_bsc_widget;




import android.appwidget.AppWidgetManager;

import android.content.Context;
import android.content.Intent;


import android.content.SharedPreferences;
import android.graphics.Bitmap;


import android.os.SystemClock;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;


import java.util.ArrayList;

import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetItemFactory(getApplicationContext(), intent);
    }

    class WidgetItemFactory implements RemoteViewsFactory {

        private Context context;
        private int appWidgetId;

        private List<String> data = new ArrayList<String>();
        private List<String> contracts = new ArrayList<String>();
        private List<Bitmap> logoList;
        private String existing;
        private boolean percent;
        private String decimals;
        private String refresh;


        private String background = "";
        private Logic logic;

        private final ExecutorService executor = Executors.newSingleThreadExecutor();



        void beginThread() {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(logic == null) {
                            logic = new Logic(contracts, existing, percent, decimals);
                            logic.beginDataRetrieval();

                            data = logic.getData();
                            data.remove("loading");
                            logoList = logic.getStackLogos();

                        }

                        else {

                            logic.update();

                            data = logic.getData();
                            System.out.println("updating");
                        }

                        AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_stack_view);
                        SystemClock.sleep(Integer.parseInt(refresh) * 1000);
                    }
                    catch(Exception e) {
                        AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_stack_view);
                        SystemClock.sleep(30000); // 30 sec timeout
                        e.printStackTrace();
                    }
                }
            });
        }


        public WidgetItemFactory(Context context, Intent intent) {

            this.context = context;
            this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }



        @Override
        public void onCreate() {

            data.add("loading...");
            loadParameters();


        }

        public void loadParameters() {



            SharedPreferences sharedPrefContract = getSharedPreferences("contractaddr" + appWidgetId, 0);
            contracts.add(sharedPrefContract.getString("CA1", ""));
            contracts.add(sharedPrefContract.getString("CA2", ""));
            contracts.add(sharedPrefContract.getString("CA3", ""));


            SharedPreferences sharedPrefBackground = getSharedPreferences("background", 0);
            background = sharedPrefBackground.getString("background", "");

            SharedPreferences sharedPrefCoin = getSharedPreferences("existing" + appWidgetId, 0);
            existing = sharedPrefCoin.getString("coin", "");

            SharedPreferences sharedPrefSettings = getSharedPreferences("settings" + appWidgetId, 0);
            percent = sharedPrefSettings.getBoolean("percent", false);
            decimals = sharedPrefSettings.getString("decimals", "");
            refresh = sharedPrefSettings.getString("refresh", "");
            refresh = refresh.substring(0, refresh.length() - 1);




        }

        @Override
        public void onDataSetChanged() {
            beginThread();


        }

        @Override
        public void onDestroy() {
            // close data source

        }

        @Override
        public int getCount() {
            if(data != null) {
                return data.size();
            }
            return 0;
        }

        @Override
        public RemoteViews getViewAt(int i) {

            if(data == null) {
                return null;
            }




            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_item);

            if(logoList != null) {
                views.setImageViewBitmap(R.id.widget_image, logoList.get(i));
            }

            switch(background) {
                case "king":
                    views.setInt(R.id.widget_item_background, "setBackgroundResource", R.drawable.king_yna);
                    break;

                case "darya":
                    views.setInt(R.id.widget_item_background, "setBackgroundResource", R.drawable.calm_darya);
                    break;

                case "lost":
                    views.setInt(R.id.widget_item_background, "setBackgroundResource", R.drawable.forever_lost);
                    break;
                case "space":
                    views.setInt(R.id.widget_item_background, "setBackgroundResource", R.drawable.deep_space);
                    break;

                default:
                    views.setInt(R.id.widget_item_background, "setBackgroundResource", R.drawable.vice_city);
                    break;
            }



            views.setTextViewText(R.id.widget_item_text, data.get(i));

            Intent fillIntent = new Intent();
            fillIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            views.setOnClickFillInIntent(R.id.widget_item_text, fillIntent);


            return views;
        }

        @Override
        public RemoteViews getLoadingView() {

            return null;
        }


        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}

