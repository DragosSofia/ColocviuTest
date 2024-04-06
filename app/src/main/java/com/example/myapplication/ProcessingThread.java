package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread{
    private int sum;
    private Context context;
    private boolean isRunning = true;
    private double mediaArithmetic, mediaGeometrica;
    public ProcessingThread(Context context, int input1, int input2){
        this.context = context;
        this.sum = input1 + input2;
        this.mediaArithmetic = (input1 + input2) / 2;
        this.mediaGeometrica = Math.sqrt(input1 * input2);

    }

    public void sendMessage(){
        Intent intent = new Intent();
        intent.setAction("com.example.myapplication.ACTION_STRING");

        intent.putExtra(Constants.MediaAritmetica, mediaArithmetic);
        intent.putExtra(Constants.MediaGeometrica, mediaGeometrica);

        context.sendBroadcast(intent);
    }

    public void run(){
        while(isRunning){
            try{
                Log.d("PracticalTest01Service", "Sending brodcast mesage");
                sendMessage();
                sleep(10000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void stopThread(){
        isRunning = false;
    }
}
