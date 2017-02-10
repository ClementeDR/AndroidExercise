package com.clementedirosa.verificaservice;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TEST_SERVICE";

    TextView mLabel;
    int mCount;
    Button mBtn1;
    Button mBtn2;
    Intent mStartIntent;
    private boolean mBound;

    BroadcastReceiver mBReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            mLabel.setText("end");
        }

    };

    ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLabel = (TextView) findViewById(R.id.textView);
        mBtn1 = (Button) findViewById(R.id.button);
        mBtn2 = (Button) findViewById(R.id.button2);

        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLabel.setText("click " + mCount++);
            }
        });

        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalBroadcastManager.getInstance(MainActivity.this)
                        .sendBroadcast(new Intent("START_LONG"));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        mStartIntent = new Intent(this, TestIntentService.class);

        Bundle vBundle = new Bundle();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());
        vBundle.putString("KEY!",currentDateandTime );
        mStartIntent.putExtras(vBundle);
        startService(mStartIntent);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mBReceiver, new IntentFilter("TIME_END"));

//        bindService(new Intent(this, TestService.class), mServiceConnection, Context.BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();

        stopService(mStartIntent);

        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mBReceiver);
//        if (mBound){
//            unbindService(mServiceConnection);
//            mBound = false;
//        }

        Log.d(TAG, "onStop: ");
    }







}
