package com.clementedirosa.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

/**
 * Created by clementedirosa on 13/01/2017.
 */

public class RandomService extends Service {
    private MyBinder mBinder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public int getRandomNumber(){
        Random vRand = new Random();
        return vRand.nextInt();
    }

    public class MyBinder extends Binder{
        public RandomService getService(){
            return RandomService.this;
        }
    }
}
