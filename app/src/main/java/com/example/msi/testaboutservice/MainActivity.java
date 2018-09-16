package com.example.msi.testaboutservice;

import android.app.ActivityManager;
import android.app.ApplicationErrorReport;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyService.MyBinder binder = null;
    Button button = null;
    Button button2 = null;
    Button button3 = null;
    Button button4 = null;
    Intent intent = null;
    Boolean running = null;


    /*service必须显示启动*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        intent = new Intent("com.example.msi.testaboutservice.MyService");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("a", "bundleData");
                intent.putExtras(bundle);
                intent.setClass(MainActivity.this, MyService.class);
                startService(intent);
                running = isServiceRunning(MainActivity.this, "com.example.msi.testaboutservice.MyService");
                Log.d("running", String.valueOf(running));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
                running = isServiceRunning(MainActivity.this, "com.example.msi.testaboutservice.MyService");
                Log.d("running", String.valueOf(running));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(MainActivity.this, MyService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);//此方法在https://www.cnblogs.com/liyiran/p/4638761.html学习
                running = isServiceRunning(MainActivity.this, "com.example.msi.testaboutservice.MyService");
                Log.d("running", String.valueOf(running));
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
                running = isServiceRunning(MainActivity.this, "com.example.msi.testaboutservice.MyService");
                Log.d("running", String.valueOf(running));
            }
        });
    }

    /*serviceName必须是完整的名字*/
    public static boolean isServiceRunning(Context context, String serviceName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(100);
        for (ActivityManager.RunningServiceInfo info : services) {
            String name = info.service.getClassName();
            if (serviceName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /*使用bindservice方法时需要重写*/
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyService.MyBinder) service;
            binder.setData("binderData");
            Log.d("tag", "绑定服务conn");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


}
