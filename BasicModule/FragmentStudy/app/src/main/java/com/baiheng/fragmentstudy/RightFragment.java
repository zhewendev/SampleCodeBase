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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

public class RightFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = RightFragment.class.getSimpleName();

    private Handler mHandler;
    private Button mHandlerBtn;
    private Button mBroadcastBtn;
    private Button mEventBusBtn;
    private Button mInterfaceCallbackBtn;
    private Activity mActivity;
    private ActivityListener mActivityListener;

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
        return root;
    }

    private void init(View view) {
        mHandlerBtn = (Button) view.findViewById(R.id.btn_handler_connect);
        mBroadcastBtn = (Button) view.findViewById(R.id.btn_broadcast_connect);
        mEventBusBtn = (Button) view.findViewById(R.id.btn_event_bus_connect);
        mInterfaceCallbackBtn = (Button) view.findViewById(R.id.btn_interface_callback);
        mHandlerBtn.setOnClickListener(this);
        mBroadcastBtn.setOnClickListener(this);
        mEventBusBtn.setOnClickListener(this);
        mInterfaceCallbackBtn.setOnClickListener(this);
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
            default:
                break;
        }
    }

    public interface ActivityListener{
        void listener(String message);
    }
}
