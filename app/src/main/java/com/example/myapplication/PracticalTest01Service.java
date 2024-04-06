package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class PracticalTest01Service extends Service {

    private ProcessingThread processingThread;
    public PracticalTest01Service() {
    }

    public void onCreate() {
        super.onCreate();

        //create a notification request for the service
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channelID");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentTitle("Service is running");
        builder.setContentText("Service is running in background");
        Notification notification = builder.build();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("PracticalTest01Service", "onStartCommand() callback method was invoked");

        processingThread = new ProcessingThread(PracticalTest01Service.this, intent.getIntExtra(Constants.editText1, 0), intent.getIntExtra(Constants.editText2, 0));
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
        processingThread.stopThread();
    }
}