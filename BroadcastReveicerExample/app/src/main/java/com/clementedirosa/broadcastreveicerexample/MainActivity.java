package com.clementedirosa.broadcastreveicerexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.clementedirosa.broadcastreveicerexample.receiver.MyBroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    private TextView mTextView;
    private MyBroadcastReceiver mReceiver =  new MyBroadcastReceiver();

    private BroadcastReceiver mSecondReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           // Log.d(TAG, "onReceive: " + " state " + intent.getExtras().getBoolean("state"));
           // mTextView.setText("State " + intent.getExtras().getBoolean("state"));
          //  mTextView.setText(intent.getAction());


            //CODE FOR BATTERY -- TEST
            if (intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)){
                String vMessage = ( intent.getExtras().getBoolean("state")) ? "Airplane mode on" : "Airplane mode off";
                mTextView.setText(vMessage);
            } else {
                Intent vBatteryStatus = intent;
                int status = vBatteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                        status == BatteryManager.BATTERY_STATUS_FULL;
                int chargePlug = vBatteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
                boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;


                if (isCharging) {
                    mTextView.setText("In carica");
                    if (usbCharge) {
                        mTextView.append("\n Usb Charge");
                    } else if (acCharge) {
                        mTextView.append("\n acCharge");
                    } else {
                        mTextView.append("\n Other Charge");
                    }
                } else {
                    mTextView.setText("\n Non in carica");
                }

                int level = vBatteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                mTextView.append("\n Batteri power: " + level);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);

        IntentFilter vFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        vFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        //vFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(mSecondReceiver, vFilter);
        /*
         see goAsync()
         */
        //registerReceiver(mReceiver, vFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mSecondReceiver);
        //unregisterReceiver(mReceiver);
    }
}
