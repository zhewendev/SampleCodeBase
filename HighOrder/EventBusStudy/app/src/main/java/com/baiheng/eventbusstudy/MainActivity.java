package com.baiheng.eventbusstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button mButton;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 注册EventBus
        EventBus.getDefault().register(this);
        mButton = (Button) findViewById(R.id.btn_try);
        mTv = (TextView) findViewById(R.id.tv);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        String msg = "onEventMainThread收到消息：" + event.getmMsg();
        Log.d(TAG, msg);
        mTv.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onEventMainThread(SecondEvent event) {
        String msg = "onEventMainThread收到消息：" + event.getMsg();
        Log.d(TAG, msg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(ThirdEvent event) {
        String msg = "onEvent收到消息：" + event.getMsg();
        Log.d(TAG, msg);
//        EventBus.getDefault().removeStickyEvent(event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 2, sticky = true)
    public void eventMessageHander(FirstEvent event) {
        String msg = "eventMessageHandle收到消息：" + event.getmMsg();
        Log.d(TAG, msg);
    }

    @Subscribe
    public void onEventBackgroundThread() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
