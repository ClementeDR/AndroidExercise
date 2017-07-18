package com.example.clemente.backgroundtimer;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

public class TimerFragment extends Fragment {
    private static final String TAG = "timerFrag";
    MyTimer myTimer;
    MyAsyncTimer mMyAsync;
//    Button test;

    TimerFede mFede;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onTimerValue(intent.getStringExtra(TAG));

        }

    };

    private IOTimerUpdate mListener = new IOTimerUpdate() {
        @Override
        public void onUpdateValue(String aValue) {

        }
    };

    public interface IOTimerUpdate{
        void onUpdateValue(String aValue);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IOTimerUpdate)
            mListener = (IOTimerUpdate)context;
        Log.d(TAG, "onAttach");
    }

    public TimerFragment() {
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mReceiver);
//        myTimer.stop();
//        mMyAsync.cancel(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        Log.d(TAG, "onDetach");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSave");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        return null;
    }

    public static TimerFragment getInstance(){
        return new TimerFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setRetainInstance(true);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mReceiver, new IntentFilter("TIMER"));
//        myTimer = new MyTimer(this);
//        Thread vTh = new Thread(new MyTimer(this));
//        vTh.start();



        mMyAsync = new MyAsyncTimer();
//        mMyAsync.execute();

        mFede = new TimerFede(this, 0, 0);
        Thread vTh = new Thread(mFede);
        vTh.start();

    }

    public void start(){
//        mMyAsync.cancel(true);
//        Log.d(TAG, "start: Cancell");
//
//        mMyAsync = new MyAsyncTimer();
//        mMyAsync.execute();

        mFede.lap();
    }


    private void onTimerValue(String aTime){
        Log.d(TAG, "onTimerValue " + aTime);
        if (mListener !=  null)
            mListener.onUpdateValue(aTime);
    }

    private class MyAsyncTimer extends AsyncTask<Void, Integer, String>{

        @Override
        protected String doInBackground(Void... params) {

            int vIndex = 0;
            while (!isCancelled()) {
                publishProgress(vIndex);
                vIndex++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException aE) {
                    aE.printStackTrace();
                }
            }
            return "Finish";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            int tempValue = values[0];
            int hour = tempValue / 3600;
            tempValue = (tempValue % 3600);
            int minutes = tempValue /60;
            tempValue = (tempValue % 60);

            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent("TIMER").putExtra(TAG, "" + hour + ":" + minutes + ":" + tempValue));

//            onTimerValue("" + hour + ":" + minutes + ":" + tempValue);

            Log.d(TAG, "onProgressUpdate: " + values[0]);
        }

        @Override
        protected void onPostExecute(String aS) {

            Log.d(TAG, "onPostExecute: " + aS);


            super.onPostExecute(aS);
        }

    }

    private static class MyTimer implements Runnable{

        int mCounter;
        boolean mRunning;
        WeakReference<TimerFragment> mRef;

        public MyTimer(TimerFragment aRef){
            mRef = new WeakReference<TimerFragment>(aRef);
            mRunning = true;
        }

        public void stop(){
            mRunning = false;
        }

        @Override
        public void run() {
            while (mRunning){
                if(mRef.get() != null){
                    mCounter++;
                    int tempValue = mCounter;
                    int hour = tempValue / 3600;
                    tempValue = (tempValue % 3600);
                    int minutes = tempValue /60;
                    tempValue = (tempValue % 60);

                    mRef.get().onTimerValue("" + hour + ":" + minutes + ":" + tempValue);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
