package com.zhewen.jsbridgelab

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.zhewen.jsbridge.jsinterface.OnBridgeCallback
import com.zhewen.jsbridge.webview.BridgeWebView

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }
    private val mButton:Button by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.button) }
    private val mMessageTv:TextView by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.message) }
    private val mBridgeWebView:BridgeWebView by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.webview) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        findViewById<Button>(findViewById(R.id.jump_btn)).setOnClickListener {
            val intent = Intent()
            intent.setClass(this,SecondActivity::class.java)
            startActivity(intent)
        }
        mBridgeWebView.webChromeClient = object :WebChromeClient() {
        }
        mBridgeWebView.addJavascriptInterface(MainJavascriptInterface(mBridgeWebView.getCallbacks(),mBridgeWebView),"android")
        mBridgeWebView.setGson(Gson())
        mBridgeWebView.loadUrl("file:///android_asset/demo.html")
        mButton.setOnClickListener {
            mBridgeWebView.callHandler("functionInJs","data from Native by click",object : OnBridgeCallback{
                @SuppressLint("SetTextI18n")
                override fun onCallback(data: String?) {
                    mMessageTv.text = "result from JS = $data"
                    Toast.makeText(this@MainActivity,"result from Js = $data",Toast.LENGTH_LONG).show()
                }
            })
        }

    }
}