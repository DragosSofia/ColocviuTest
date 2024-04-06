package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //get the media arithmetic and media geometrica
        double mediaAritmetica = intent.getDoubleExtra(Constants.MediaAritmetica, 0);
        double mediaGeometrica = intent.getDoubleExtra(Constants.MediaGeometrica, 0);

        //display the media arithmetic and media geometrica
        Log.d(Constants.TAG, "Media Aritmetica: " + mediaAritmetica);
    }
}
