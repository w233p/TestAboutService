package com.example.msi.testaboutservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {

    String data = null;
    String a = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("tag", "onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d("tag", "onStart");
        a = intent.getStringExtra("a");
        Log.d("a:", a);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("tag", "onDestroy");
    }

    @Nullable
    @Override
    public MyBinder onBind(Intent intent) {
        Log.d("tag", "onBind");
        return new MyBinder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d("tag", "onRebind");

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("tag", "onUnbind");
        Log.d("data", data);
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {
        public void setData(String data) {
            MyService.this.data = data;
        }
    }
}
