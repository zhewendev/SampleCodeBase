package com.zhewen.eyepetizer

import android.app.Application
import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


class EyepetizerApplication : Application() {
    
    companion object {
        private const val TAG : String = "EyepetizerApplication"
        var mContext: Context? = null
        fun getContext() : Context {
            return mContext!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
        initConfig()
    }

    private fun initConfig() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)  // 隐藏线程信息 默认：显示
            .methodCount(0)         // 决定打印多少行（每一行代表一个方法）默认：2
            .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
            .tag(TAG)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}