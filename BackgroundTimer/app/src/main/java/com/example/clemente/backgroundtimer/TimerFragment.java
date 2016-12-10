package com.example.clemente.backgroundtimer;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

public class TimerFragment extends Fragment {
    private static final String TAG = "timerFrag";
    MyTimer myTimer;
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
        mListener = null;
        myTimer.stop();
    }

    @Override
    public void onDetach() {
        super.onDetach();

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
        myTimer = new MyTimer(this);
        Thread vTh = new Thread(new MyTimer(this));
        vTh.start();
    }

    private void onTimerValue(String aTime){
        Log.d(TAG, "onTimerValue " + aTime);
        mListener.onUpdateValue(aTime);
    }

    private static class MyTimer implements Runnable{

        int mCounter;
        int mm;
        int ss;
        String min;
        String sec;
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
                    ss++;
                    if(ss == 60){
                        mm++;
                        ss = 0;
                    }
                    if (mm < 10)
                        min = "0" + mm;
                    else
                        min = "" + mm;
                    if(ss < 10)
                        sec = "0" + ss;
                    else
                        sec = "" + ss;

                    mRef.get().onTimerValue("" + min + ":" + sec);
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
