package com.training.simpleaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyRemoteService extends Service {
    public MyRemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private final IMyCalculatorAidlInterface.Stub iBinder = new IMyCalculatorAidlInterface.Stub() {
        @Override
        public int addTwoNumber(int a, int b) throws RemoteException {
            return a + b;
        }
    };
}