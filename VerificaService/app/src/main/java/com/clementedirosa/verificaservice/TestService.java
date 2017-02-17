package com.clementedirosa.verificaservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by clementedirosa on 27/01/2017.
 */

public class TestService extends Service {
    private static final String TAG = "Service";

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: TestService");
           // timerLog();
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");

        Bundle vBundle = intent.getExtras();
        if (vBundle != null){
            Log.d(TAG, "vBundleString: " + vBundle.getString("KEY!"));
        }
        IntentFilter vFilter = new IntentFilter("START_LONG");
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver,vFilter);
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }


    private MyBinder mBinder = new MyBinder();

    public class MyBinder extends Binder {
        public TestService getService(){
            return TestService.this;
        }
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
        stopSelf();
    }
}
