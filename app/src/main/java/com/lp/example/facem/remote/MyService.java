package com.lp.example.facem.remote;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.RemoteException;

import com.lp.example.facem.IMyService;

public class MyService extends Service {
    private IMyService.Stub mIBinder = new IMyService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            return;
        }

        @Override
        public int plus(int a, int b) throws RemoteException {
            return a+b;
        }
    };

    public MyService() {

    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mIBinder;
    }
}
