package com.zhewen.kotlinsample

import android.app.Application
import android.content.Context
import android.content.ContextWrapper


private lateinit var instance: Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}

object AppContext : ContextWrapper(instance)