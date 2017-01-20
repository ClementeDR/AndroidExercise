package com.clementedirosa.broadcastreveicerexample.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by clementedirosa on 20/01/2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: " + intent.getAction());
        Log.d(TAG, "onReceive: " + " state " + intent.getExtras().getBoolean("state"));
        Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();
    }
}
