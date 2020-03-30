package com.baiheng.activitystudy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 *
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = SecondActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_second);
        if (savedInstanceState != null) {
            String tempData = savedInstanceState.getString("data_key");
            Log.d(TAG, tempData);
        }
        getIntentData();
        init();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_value");
        Log.d(TAG,"extra_value" + data);
    }

    private void init() {
        Button dataReturnBtn = (Button) findViewById(R.id.btn_data_return);
        dataReturnBtn.setOnClickListener(this);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        outState.putString("data_key", tempData);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_data_return:
                Intent intent = new Intent();
                intent.putExtra("data_return","Hello MainActivity");
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("data_return","Hello MainActivity");
        setResult(RESULT_OK, intent);
        finish();
    }
}
