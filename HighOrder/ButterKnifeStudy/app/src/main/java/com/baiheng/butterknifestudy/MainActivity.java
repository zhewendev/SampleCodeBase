package com.baiheng.butterknifestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_start)
    Button mBtnStartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
