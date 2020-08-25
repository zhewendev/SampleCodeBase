package com.zhewen.eyepetizer.ui

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.zhewen.eyepetizer.R
import com.zhewen.eyepetizer.common.base.BaseSupportFragment

class VideoSplashFragment: BaseSupportFragment() {


    override fun getLayoutId(): Int = R.layout.fragment_video_splash

    override fun initView(savedInstanceState: Bundle?) {

       setVideoObserver()
    }

    private fun playVideo() {
        val path = R.raw.landing
        with()

    }

    private fun setVideoObserver() {
        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onVideoStart() {

            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onVideoResume() {

            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun onVideoPause() {

            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onVideoStop() {

            }
        })
    }
}
