package com.zhewen.jetpacklab

import android.app.Application
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.github.moduth.blockcanary.BlockCanary
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class JetpackApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        BlockCanary.install(this, MyBlockCanaryContext()).start() //在主进程初始化调用
        ProcessLifecycleOwner.get().lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                super.onCreate(owner)
                Log.d("JetpackApplication","onCreate")
            }

            override fun onStart(owner: LifecycleOwner) {
                super.onStart(owner)
                Log.d("JetpackApplication","onStart")
            }

            override fun onResume(owner: LifecycleOwner) {
                super.onResume(owner)
                Log.d("JetpackApplication","onResume")
            }

            override fun onPause(owner: LifecycleOwner) {
                super.onPause(owner)
                Log.d("JetpackApplication","onPause")
            }

            override fun onStop(owner: LifecycleOwner) {
                super.onStop(owner)
                Log.d("JetpackApplication","onStop")
            }

            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                Log.d("JetpackApplication","onDestroy")
            }
        })
    }
}