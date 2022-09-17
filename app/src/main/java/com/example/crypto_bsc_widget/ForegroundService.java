package com.example.crypto_bsc_widget;

import static com.example.crypto_bsc_widget.App.CHANNEL_ID;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

import android.os.IBinder;
import android.os.SystemClock;



import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForegroundService extends Service {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static boolean status = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(status) {
            return START_REDELIVER_INTENT;
        }

        status = true;

        String contractAddress = intent.getStringExtra("inputExtra");
        startForeground(1, getMyActivityNotification("LOADING..."));
        beginThread(contractAddress);
        return START_REDELIVER_INTENT;
    }



    public Notification getMyActivityNotification(String text) {


        Intent notificationIntent = new Intent(this, ForegroundActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);



        return new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Crypto Smart Widget")
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_baseline_widgets_24)
                .setContentIntent(pendingIntent)
                .build();

    }






    public void beginThread(String input) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                int rep = 0;
                List<String> contracts = new ArrayList<String>();
                contracts.add(input);
                Logic logic = new Logic(contracts, "", false, "8");
                List<String> data;
                while(status) {
                    try {
                        if(rep == 0) {
                            logic.beginDataRetrieval();
                            data = logic.getData();
                        }
                        else {
                            logic.update();
                            data = logic.getData();
                        }
                        rep++;

                        if(data.size() > 0) {
                            String value = logic.getData().get(0);
                            Notification notification = getMyActivityNotification(value);
                            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            mNotificationManager.notify(1, notification);
                        }

                        SystemClock.sleep(30000);

                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        stopSelf();
                        return;

                    }
                }
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        stopSelf();
        stopForeground(true);
        status = false;
        super.onDestroy();
    }
}
