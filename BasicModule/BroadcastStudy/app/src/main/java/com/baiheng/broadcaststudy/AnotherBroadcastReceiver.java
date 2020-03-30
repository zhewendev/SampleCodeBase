package com.baiheng.broadcaststudy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 自定义的广播接收器，用以接收自定发送的广播
 */
public class AnotherBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"AnotherBroadcastReceiver",Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}
