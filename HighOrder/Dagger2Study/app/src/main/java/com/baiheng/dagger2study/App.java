package com.baiheng.dagger2study;

import android.util.Log;

import javax.inject.Inject;

public class App {
    @Inject
    Logger logger;

    @Inject
    Logger logger2;

    public App(){
        DaggerAppComponent.create().inject(this);
    }

    public void printMessage() {
        Log.d("App", "app:" + this);
        logger.printMessage();
        logger2.printMessage();
    }
}
