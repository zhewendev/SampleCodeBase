package com.baiheng.dagger2study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

/**
 * Dagger2使用示例工程主活动
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Car car = new Car();
        car.getEngineA().run();
        car.getEngineB().run();
        App app = new App();
        app.printMessage();
    }
}
