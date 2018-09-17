package com.example.msi.testaboutservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
    }

    /*在此方法里面运行耗时操作*/
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, Thread.currentThread().getName() + "---" + intent.getStringExtra("IntentServiceData"));
        for (int i = 0; i < 500; i++) {
            Log.i(TAG, "i=" + i);
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
