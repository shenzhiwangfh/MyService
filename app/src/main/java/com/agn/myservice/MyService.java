package com.agn.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyService extends Service {

    private final static String TAG = "MyService";

    private MyBinder myBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i("onBind");
        return myBinder;
    }

    @Override
    public void onCreate() {
        LogUtils.i("onCreate");
        //Toast.makeText(this, "Start Service", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        LogUtils.i("onDestroy");
    }

    class MyBinder extends Binder {
        public void start() {
            LogUtils.i("start");
        }
    }
}
