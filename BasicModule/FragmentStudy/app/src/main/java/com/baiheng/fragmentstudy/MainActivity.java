package com.baiheng.fragmentstudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;

/**
 * Fragment的主活动页面
 *
 */
public class MainActivity extends AppCompatActivity implements RightFragment.ActivityListener{
    private static final String TAG = MainActivity.class.getSimpleName();

    public MyHandler mMyHandler = new MyHandler(this);
    private FragmentManager mFragmentManager;
    private LeftFragment mLeftFragment;

    public static class MyHandler extends Handler {
        private WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.d(TAG,"正在处理RightFragment的Handler消息");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);   //注册EventBus
        mFragmentManager = getSupportFragmentManager();
        mLeftFragment = new LeftFragment();
        dynamicLoadFragment(mLeftFragment,R.id.first_fragment_contain);
        RightFragment rightFragment = new RightFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key","setArguments");
        rightFragment.setArguments(bundle);
        dynamicLoadFragment(rightFragment,R.id.fragment_contain);
        registerBroadcastReceiver();
    }

    /**
     * 动态加载Fragment
     * @param fragment
     */
    private void dynamicLoadFragment(Fragment fragment,int resId) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(resId,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void listener(String message) {
        Toast.makeText(this,"interface callback" + message,Toast.LENGTH_LONG).show();
    }


    /**
     * 接收消息
     * @param fragmentEvent
     */
    @Subscribe
    public void onEventMainThred(FragmentEvent fragmentEvent) {
        Log.d(TAG,"userName = " + fragmentEvent.getUserName() + "userAge = " + fragmentEvent.getUserAge());
        Toast.makeText(this,"userName = " + fragmentEvent.getUserName() + "userAge = " + fragmentEvent.getUserAge(),Toast.LENGTH_LONG).show();
    }

    /**
     * 注册广播
     */
    private void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_name");
        registerReceiver(mBroadcastReceiver,intentFilter);
    }

    /**
     * 创建广播接收器
     */
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action_name")) {
                Toast.makeText(MainActivity.this,"广播",Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
        unregisterReceiver(mBroadcastReceiver);
        EventBus.getDefault().unregister(this);
    }
}
