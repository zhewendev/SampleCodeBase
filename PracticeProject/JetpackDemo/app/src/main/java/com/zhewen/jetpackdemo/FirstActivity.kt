package com.zhewen.jetpackdemo

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.zhewen.jetpackdemo.lifecycle.MyObserver

class FirstActivity:Activity(),LifecycleOwner {

    private lateinit var mLifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLifecycleRegistry = LifecycleRegistry(this)
        lifecycle.addObserver(MyObserver())
        mLifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    override fun onStart() {
        super.onStart()
        mLifecycleRegistry.currentState = Lifecycle.State.STARTED

    }

    override fun onDestroy() {
        super.onDestroy()
        mLifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    override fun onPause() {
        super.onPause()
        mLifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }
}