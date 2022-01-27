package com.zhewen.jsbridgelab

import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity:AppCompatActivity(R.layout.activity_second) {

    companion object {
        const val TAG = "SecondActivity"
    }

    private val mButton: Button by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.button) }
    private val mMessageTv: TextView by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.message) }
    private val mBridgeWebView: WebView by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.webview) }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initView()
    }

    private fun initView() {

    }
}