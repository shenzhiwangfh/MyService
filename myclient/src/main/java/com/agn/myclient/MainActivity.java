package com.agn.myclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.agn.remoteservice.IMyAidlInterface;

public class MainActivity extends AppCompatActivity implements ServiceConnection {

    private final static String TAG = "MainActivity";

    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.set_value).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "bindService");
                Intent intent = new Intent();
                intent.setAction("com.android.MyRemoteService");
                intent.setPackage("com.agn.remoteservice");
                bindService(intent, MainActivity.this, Context.BIND_AUTO_CREATE);
            }
        });
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.i(TAG, "onServiceConnected");
        iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
        try {
            Log.i(TAG, "iMyAidlInterface.setValue");
            iMyAidlInterface.setValue(1);
        } catch (RemoteException e) {
            Log.i(TAG, "RemoteException");
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
}