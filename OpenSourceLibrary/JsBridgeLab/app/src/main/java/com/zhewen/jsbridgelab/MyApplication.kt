package com.zhewen.jsbridgelab

import android.app.Application

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}