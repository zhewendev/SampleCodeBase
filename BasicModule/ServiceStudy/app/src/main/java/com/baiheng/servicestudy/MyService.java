package com.baiheng.servicestudy;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 自定义的Service
 */
public class MyService extends Service {
    private static final String TAG = MyService.class.getSimpleName();

    private DownloadBinder mBinder = new DownloadBinder();

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d(TAG,"startDownload");

        }
        public int getProgress() {
            Log.d(TAG,"getProgress");
            return 0;
        }
    }
}
