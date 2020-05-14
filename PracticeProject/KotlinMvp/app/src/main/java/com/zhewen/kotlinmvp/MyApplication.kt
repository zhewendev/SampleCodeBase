package com.zhewen.kotlinmvp

import android.app.Application
import com.squareup.leakcanary.RefWatcher
/**
* @author: zhewen
* created: 2020/5/14
* desc: Initializing Environment Configuration
*/
class MyApplication : Application() {
    private var refWatcher: RefWatcher? = null  //todo

    companion object {
        private val TAG = MyApplication::class.java.simpleName

    }
}