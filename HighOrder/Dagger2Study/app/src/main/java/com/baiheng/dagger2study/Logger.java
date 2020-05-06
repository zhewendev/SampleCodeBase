package com.baiheng.dagger2study;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Logger {
    @Inject
    public Logger(){

    }

    public void printMessage() {
        Log.d("Logger", this.toString());
    }
}
