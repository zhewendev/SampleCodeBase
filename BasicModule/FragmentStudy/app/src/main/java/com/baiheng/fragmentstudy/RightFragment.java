package com.baiheng.fragmentstudy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.greenrobot.eventbus.EventBus;

public class RightFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = RightFragment.class.getSimpleName();

    private Handler mHandler;
    private Activity mActivity;
    private ActivityListener mActivityListener;
    private Bundle mBundle;

    @Override
    public void onAttach(@NonNull Context context) {
        Log.d(TAG,"onAttach");
        super.onAttach(context);
        mActivity = (Activity) context;
        mActivityListener = (ActivityListener) context;
        if (context instanceof MainActivity) {
            mHandler = ((MainActivity)context).mMyHandler;
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View root = inflater.inflate(R.layout.fragment_right,container,false);
        init(root);
        mBundle = this.getArguments();
        return root;
    }

    private void init(View view) {
        Button mHandlerBtn = (Button) view.findViewById(R.id.btn_handler_connect);
        Button mBroadcastBtn = (Button) view.findViewById(R.id.btn_broadcast_connect);
        Button mEventBusBtn = (Button) view.findViewById(R.id.btn_event_bus_connect);
        Button mInterfaceCallbackBtn = (Button) view.findViewById(R.id.btn_interface_callback);
        Button mArgumentBtn = (Button) view.findViewById(R.id.btn_get_arguments);
        Button mSendThirdFragmentValueBtn = (Button) view.findViewById(R.id.btn_send_third_fragment_value);
        Button mConnectOtherFragmentBtn = (Button) view.findViewById(R.id.btn_connect_with_other_fragment);
        mHandlerBtn.setOnClickListener(this);
        mBroadcastBtn.setOnClickListener(this);
        mEventBusBtn.setOnClickListener(this);
        mInterfaceCallbackBtn.setOnClickListener(this);
        mArgumentBtn.setOnClickListener(this);
        mSendThirdFragmentValueBtn.setOnClickListener(this);
        mConnectOtherFragmentBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_handler_connect:
                if (mHandler != null) {
                    Message message = Message.obtain();
                    message.what = 1;
                    mHandler.sendMessage(message);
                }
                break;
            case R.id.btn_broadcast_connect:
                Intent intent = new Intent("action_name");
                mActivity.sendBroadcast(intent);
                break;
            case R.id.btn_event_bus_connect:
                FragmentEvent fragmentEvent = new FragmentEvent();
                fragmentEvent.setUserAge(20);
                fragmentEvent.setUserName("龙天越");
                EventBus.getDefault().post(fragmentEvent);
                break;
            case R.id.btn_interface_callback:
                mActivityListener.listener("RightFragment Callback");
                break;
            case R.id.btn_get_arguments:
                if (mBundle != null) {
                    Toast.makeText(mActivity,mBundle.getString("key"),Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_send_third_fragment_value:
                dynamicLoadFragment(ThirdFragment.newInstance("Hello,I'm from RightFragment page"));
                break;
            case R.id.btn_connect_with_other_fragment:
                TextView textView = mActivity.findViewById(R.id.tv_left_fragment);
                String string = textView.getText().toString();
                Toast.makeText(mActivity,string,Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    /**
     * 动态加载Fragment
     * @param fragment
     */
    private void dynamicLoadFragment(Fragment fragment) {
        FragmentManager mFragmentManager = getFragmentManager();
        assert mFragmentManager != null;
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.fragment_contain,fragment);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }

    public interface ActivityListener{
        void listener(String message);
    }
}
