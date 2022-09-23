package com.agn.myservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyIntentService2 extends IntentService {

    public final static String KEY = "key1";
    public final static String VALUE1 = "value1";
    public final static String VALUE2 = "value2";

    public MyIntentService2() {
        super("MyIntentService2");
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i("onBind2");
        return super.onBind(intent);
    }


    @Override
    public void onCreate() {
        LogUtils.i("onCreate2");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        LogUtils.i("onStart2");
        super.onStart(intent, startId);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.i("onStartCommand2");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void setIntentRedelivery(boolean enabled) {
        super.setIntentRedelivery(enabled);
        LogUtils.i("setIntentRedelivery2");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getExtras().getString(KEY);
        if (action != null && action.equals(VALUE1)) {
            LogUtils.i("onHandleIntent2 1");
        } else if (action != null && action.equals(VALUE2)) {
            LogUtils.i("onHandleIntent2 2");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        LogUtils.i("onDestroy2");
        super.onDestroy();
    }
}
