package com.baiheng.eventbusstudy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {

    private Button mBtnFirstEvent;
    private Button mBtnSecondEvent;
    private Button mBtnThirdEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mBtnFirstEvent = (Button) findViewById(R.id.btn_first_event);
        mBtnSecondEvent = (Button) findViewById(R.id.btn_second_event);
        mBtnThirdEvent = (Button) findViewById(R.id.btn_third_event);
        mBtnFirstEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FirstEvent("FirstEvent btn Clicked"));
            }
        });
        mBtnSecondEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SecondEvent("SecondEvent btn clicked"));
            }
        });
        mBtnThirdEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new ThirdEvent("ThirdEvent btn clicked"));
            }
        });
    }
}
