package com.baiheng.servicestudy;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 自定义的前台服务
 */
public class ForeService extends Service {
    private static final String TAG = ForeService.class.getSimpleName();
    private static final String ID="channel_1";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        if(Build.VERSION.SDK_INT>=26){
            setForeground();
        }else{

        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    @TargetApi(26)
    private void setForeground(){
        Notification notification=new Notification.Builder (this,ID)
                .setContentTitle ("收到一条重要通知")
                .setContentText ("这是重要通知")
                .setSmallIcon (R.mipmap.ic_launcher)
                .setLargeIcon (BitmapFactory.decodeResource (getResources (),R.mipmap.ic_launcher))
                .build ();
        startForeground (1,notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        stopForeground(true);
    }
}
