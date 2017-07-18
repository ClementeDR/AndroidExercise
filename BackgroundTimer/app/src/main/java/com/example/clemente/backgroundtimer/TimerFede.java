package com.example.clemente.backgroundtimer;

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by clementedirosa on 17/06/2017.
 */

public class TimerFede implements Runnable{

    int mCounter;
    int mLapTime;
    boolean mRunning;
    WeakReference<TimerFragment> mRef;

    public TimerFede(TimerFragment aRef){
        mRef = new WeakReference<TimerFragment>(aRef);
        mRunning = true;
    }

    public TimerFede(TimerFragment aRef, int aCounter, int aLapTimer){
        mRef = new WeakReference<TimerFragment>(aRef);
        mRunning = true;
        mCounter = aCounter;
        mLapTime = aLapTimer;
    }

    public void stop(){
        mRunning = false;
    }

    public void lap()
    {
        mCounter += mLapTime;
        mLapTime = 0;

        //qui aggiungi alla tw il valore totale
        //aggiungi al db il giro

    }
    @Override
    public void run() {
        while (mRunning){
            if(mRef.get() != null){
                mLapTime++;
                int tempValue = mLapTime;
                int hour = tempValue / 3600;
                tempValue = (tempValue % 3600);
                int minutes = tempValue /60;
                tempValue = (tempValue % 60);

                Log.d("LAP","" + mLapTime);
                Log.d("Counter","" + mCounter);

//                mRef.get().onTimerValue("" + hour + ":" + minutes + ":" + tempValue);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
