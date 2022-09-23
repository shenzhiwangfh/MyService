package com.agn.myservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {

    public final static String KEY = "key1";
    public final static String VALUE1 = "value1";
    public final static String VALUE2 = "value2";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i("onBind");
        return super.onBind(intent);
    }


    @Override
    public void onCreate() {
        LogUtils.i("onCreate");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        LogUtils.i("onStart");
        super.onStart(intent, startId);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.i("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void setIntentRedelivery(boolean enabled) {
        super.setIntentRedelivery(enabled);
        LogUtils.i("setIntentRedelivery");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getExtras().getString(KEY);
        if (action != null && action.equals(VALUE1)) {
            LogUtils.i("onHandleIntent 1");
        } else if (action != null && action.equals(VALUE2)) {
            LogUtils.i("onHandleIntent 2");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        LogUtils.i("onDestroy");
        super.onDestroy();
    }
}
