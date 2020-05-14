package com.zhewen.mvvmstudy;

import android.view.View;
import android.widget.Toast;

public class Utils {
    public void onClickWithMe(View view) {
        Toast.makeText(view.getContext(), "调用类里的方法", Toast.LENGTH_SHORT).show();
    }
}
