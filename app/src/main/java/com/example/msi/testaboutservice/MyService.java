package com.example.msi.testaboutservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {

    String data = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("tag", "onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d("tag", "onStart");
//        Bundle bundle = intent.getBundleExtra("a");
//        String a = bundle.getString("a");
//        Log.d("a:",a);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("tag", "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
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
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder{
        public void setData(String data){
            MyService.this.data = data;
        }
    }
}
