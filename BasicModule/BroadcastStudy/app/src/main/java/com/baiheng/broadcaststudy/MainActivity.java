package com.baiheng.broadcaststudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Broadcast学习的主程序
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private IntentFilter mIntentFilter;
    private IntentFilter mAnotherIntentFilter;
    private IntentFilter mLocalIntentFilter;
    private NetworkChangeReceiver mNetworkChangeReceiver;
    private AnotherBroadcastReceiver mAnotherBroadcastReceiver;
    private LocalReceiver mLocalReceiver;
    private LocalBroadcastManager mLocalBroadcastManager;
    private Button mSendBroadcast;
    private Button mSendOrderBroadcast;
    private Button mSendLocalBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSendBroadcast = (Button) findViewById(R.id.btn_start_broadcast);
        mSendOrderBroadcast = (Button) findViewById(R.id.btn_start_order_broadcast);
        mSendLocalBroadcast = (Button) findViewById(R.id.btn_start_local_broadcast);
        mSendBroadcast.setOnClickListener(this);
        mSendOrderBroadcast.setOnClickListener(this);
        mSendLocalBroadcast.setOnClickListener(this);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        registerBroadcast();

    }

    private void registerBroadcast() {
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mNetworkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(mNetworkChangeReceiver, mIntentFilter);	//注册广播

        mAnotherIntentFilter = new IntentFilter();
        mAnotherIntentFilter.addAction("com.baiheng.broadcaststudy.MY_BROADCAST");
        mAnotherIntentFilter.setPriority(20);
        mAnotherBroadcastReceiver = new AnotherBroadcastReceiver();
        registerReceiver(mAnotherBroadcastReceiver,mAnotherIntentFilter);

        mLocalIntentFilter = new IntentFilter();
        mLocalIntentFilter.addAction("com.baiheng.broadcaststudy.MY_BROADCAST");
        mLocalReceiver = new LocalReceiver();
        mLocalBroadcastManager.registerReceiver(mLocalReceiver,mLocalIntentFilter);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_start_broadcast:
                Intent intent = new Intent("com.baiheng.broadcaststudy.MY_BROADCAST");
                intent.setComponent(new ComponentName("com.baiheng.broadcaststudy","com.baiheng.broadcaststudy.MyBroadcastReceiver"));
                sendBroadcast(intent);
                break;
            case R.id.btn_start_order_broadcast:
                Intent intent1 = new Intent("com.baiheng.broadcaststudy.MY_BROADCAST");
                sendOrderedBroadcast(intent1,null);
                break;
            case R.id.btn_start_local_broadcast:
                Intent intent2 = new Intent("com.baiheng.broadcaststudy.MY_BROADCAST");
                mLocalBroadcastManager.sendBroadcast(intent2);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetworkChangeReceiver);
        unregisterReceiver(mAnotherBroadcastReceiver);
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
        Log.d(TAG,"onDestroy");
    }

    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                Toast.makeText(context, "network is Connected", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class LocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}
