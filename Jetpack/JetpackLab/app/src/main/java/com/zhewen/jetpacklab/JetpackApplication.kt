package com.zhewen.jetpacklab

import android.app.Application
import com.github.moduth.blockcanary.BlockCanary
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class JetpackApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        BlockCanary.install(this, MyBlockCanaryContext()).start() //在主进程初始化调用
    }
}