package com.training.simpleaidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Yoke.MainActivity";

    IMyCalculatorAidlInterface iMyCalculatorAidlInterface;

    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> add());

        Intent intent = new Intent(this, MyRemoteService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void add() {
        int value1 = 5;
        int value2 = 10;
        try {
            int result = iMyCalculatorAidlInterface.addTwoNumber(value1, value2);
            Log.e(TAG, "Result: " + result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinderService) {
            iMyCalculatorAidlInterface = IMyCalculatorAidlInterface.Stub.asInterface(iBinderService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}