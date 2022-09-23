package com.agn.myservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        Button btn = findViewById(R.id.start_service);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, MyService.class);
                startService(i);
            }
        });

        Button btn2 = findViewById(R.id.bind_service);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, MyService.class);
                bindService(i, mConnection, Context.BIND_AUTO_CREATE);
            }
        });


        Button btn3 = findViewById(R.id.start_iservice1);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startIntentService(
                        "com.agn.MyIntentService",
                        "com.agn.myservice",
                        MyIntentService.KEY,
                        MyIntentService.VALUE1);
                startIntentService(
                        "com.agn.MyIntentService",
                        "com.agn.myservice",
                        MyIntentService.KEY,
                        MyIntentService.VALUE2);

                LogUtils.i("clicked iservice1");
            }
        });

        Button btn4 = findViewById(R.id.start_iservice2);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startIntentService(
                        "com.agn.MyIntentService2",
                        "com.agn.myservice",
                        MyIntentService.KEY,
                        MyIntentService.VALUE1);
            }
        });
    }

    private void startIntentService(String action, String packageName, String key, String value) {
        Intent startServiceIntent = new Intent();
        startServiceIntent.setAction(action);
        startServiceIntent.setPackage(packageName);
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        startServiceIntent.putExtras(bundle);
        startService(startServiceIntent);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtils.i("onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtils.i("onServiceDisconnected");
        }
    };
}