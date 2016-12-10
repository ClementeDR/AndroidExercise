package com.example.clemente.countdown;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class Countdown extends Fragment {
    private static final String VAL_BUNDLE = "aValue";
    private int time;
    Timer mTimer;
    public interface IOUpdateTime{
        void updateTime(int aTime);
    }

    private IOUpdateTime mListener = new IOUpdateTime() {
        @Override
        public void updateTime(int aTime) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListener = null;
        mTimer.stop();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Bundle bundle = getArguments();
        if (bundle != null)
            time =  bundle.getInt(VAL_BUNDLE);
        mTimer = new Timer(this, time);
        Thread vTh = new Thread(mTimer);
        vTh.start();



    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IOUpdateTime)
            mListener = (IOUpdateTime)context;
    }

    public Countdown() {
        // Required empty public constructor
    }

    public static Countdown getInstance(int aValue){
        Bundle bundle = new Bundle();
        bundle.putInt(VAL_BUNDLE, aValue);
        Countdown vFrag = new Countdown();
        vFrag.setArguments(bundle);
        return vFrag;
    }

    void conta(int count){
        mListener.updateTime(count);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return null;
    }

    private static class Timer implements Runnable{
        int tempo;
        boolean mRunning;
        WeakReference<Countdown> mRef;
        public Timer(Countdown aRef, int aTime){
            mRef = new WeakReference<Countdown>(aRef);
            mRunning = true;
            tempo = aTime;
        }

        void stop(){
            mRunning = false;
        }
        @Override
        public void run() {
            while (mRunning && tempo != 0){
                if(mRef.get() != null){
                    tempo--;
                    mRef.get().conta(tempo);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else
                    stop();
            }
        }
    }

}
