package com.example.clemente.newapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";

    TextView mText;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy " + this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = (TextView) findViewById(R.id.textview);

        MyRunnable vMyRunnable = new MyRunnable(this);
        Thread vThread = new Thread(vMyRunnable);
        vThread.start();

    }
    private void updateGUI(final int aValue){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mText.setText("" + aValue);
            }
        });
    }

    @Override
    protected void finalize() throws Throwable {
        Log.d(TAG, "finalize " + this);
        super.finalize();
    }

    private static class MyRunnable implements Runnable{
        int mCounter;
        WeakReference<MainActivity> mWeak;
        boolean mRunning;
        MyRunnable(MainActivity aRef){
            mWeak = new WeakReference<MainActivity>(aRef);
            mRunning = true;
        }
        @Override
        public void run() {
            while (mRunning){
                if (mWeak.get() != null){
                    Log.d(TAG, "Counter " + mCounter + " " + mWeak.get());
                    mWeak.get().updateGUI(mCounter);
                } else {
                    mRunning = false;
                }

                Log.d(TAG, "Timer running " + this);
                mCounter++;
                //vRef.updateGUI(mCounter);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
