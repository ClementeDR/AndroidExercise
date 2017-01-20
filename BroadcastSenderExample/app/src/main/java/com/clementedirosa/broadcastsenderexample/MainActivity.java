package com.clementedirosa.broadcastsenderexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String TEST = "TEST";
    private TextView mTextView;
    private Button mBtn;
    private int mCounter;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString());
            mCounter++;
            mTextView.setText("" + mCounter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mBtn = (Button) findViewById(R.id.button);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageClick();
            }
        });

        IntentFilter vFilter = new IntentFilter(TEST);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, vFilter);

    }

    private void manageClick() {
        Log.d(TAG, "manageClick: ");
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(TEST));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }
}
