package com.clementedirosa.verificaservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by clementedirosa on 27/01/2017.
 */

public class TestIntentService extends IntentService {
    private static final String TAG = "TestIntentService";
    public TestIntentService() {
        super("TestIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: TestIntentService");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: TestIntentService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: TestIntentService"+ intent.getAction());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: TestIntentService");

        Bundle vBundle = intent.getExtras();
        if (vBundle != null){
            Log.d(TAG, "vBundleString: " + vBundle.getString("KEY!") + intent.getAction());
        }
        timerLog();
    }

    private void timerLog(){
        try{
            for(int i = 0; i < 500 ; i++){
                Thread.sleep(100);
                Log.d(TAG, "timerLog: " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupted();
        }

        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("TIME_END"));
    }
}
