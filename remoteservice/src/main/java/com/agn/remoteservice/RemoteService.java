package com.agn.remoteservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class RemoteService extends Service {

    private final static String TAG = "RemoteService";
    private final static int NOTIFICATION_ID = 10086;

    private Context mContext;
    private IMyAidlInterface.Stub myProxy = new MyProxy();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        startForeground();
    }

    /**
     * 启动前台服务
     */
    private void startForeground() {
        String channelId = createNotificationChannel(
                "com.agn.remoteservice", "ForegroundService");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, channelId);
        Notification notification = builder.setOngoing(true)
                .setSmallIcon(R.drawable.ic_baseline_directions_run_24)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(NOTIFICATION_ID, notification);
    }

    /**
     * 创建通知通道
     *
     * @param channelId
     * @param channelName
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel(String channelId, String channelName) {
        NotificationChannel chan = new NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager service = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        service.createNotificationChannel(chan);
        return channelId;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return myProxy;
    }

    public class MyProxy extends IMyAidlInterface.Stub {
        private int value;

        @Override
        public void setValue(int v) throws RemoteException {
            Log.i(TAG, "setValue," + v);
            this.value = v;
        }

        @Override
        public int getValue() throws RemoteException {
            Log.i(TAG, "getValue," + value);
            return this.value;
        }
    }
}
