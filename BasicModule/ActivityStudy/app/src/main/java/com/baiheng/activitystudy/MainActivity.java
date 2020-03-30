package com.baiheng.activitystudy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

/**
 * 主活动，用于启动各种Activity
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private String mData = "我是需要传递的数据";
    private final int mRequestCode = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Button startActivity = (Button) findViewById(R.id.start_activity);
        Button implicitStartActivity = (Button) findViewById(R.id.implicit_start_activity);
        Button startThirdActivity = (Button) findViewById(R.id.start_third_activity);
        startActivity.setOnClickListener(this);
        implicitStartActivity.setOnClickListener(this);
        startThirdActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.start_activity:
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("extra_value",mData);
                startActivityForResult(intent,mRequestCode);
                break;
            case R.id.implicit_start_activity:
                Intent intent1 = new Intent();
                intent1.setAction("com.baiheng.activitystudy.action.SECOND");
                intent1.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(intent1);
                break;
            case R.id.start_third_activity:
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_VIEW);
                intent2.addCategory(Intent.CATEGORY_DEFAULT);
                intent2.setData(Uri.parse("baiheng://baiheng.com"));
                startActivity(intent2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case mRequestCode:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d(TAG,returnedData);
                }
        }
    }
}
