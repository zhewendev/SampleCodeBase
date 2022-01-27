package com.zhewen.jsbridgelab

import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast
import com.zhewen.jsbridge.jsinterface.BaseJavascriptInterface
import com.zhewen.jsbridge.jsinterface.OnBridgeCallback
import com.zhewen.jsbridge.webview.BridgeWebView

open class MainJavascriptInterface(callbacks:HashMap<String,OnBridgeCallback>, webView:BridgeWebView):BaseJavascriptInterface(callbacks) {

    private val mWebView:BridgeWebView = webView

    override fun toast(data: String) {
        application?.let {
            Toast.makeText(it,"data from js %data",Toast.LENGTH_LONG).show()
        }
    }

    override fun phoneInfo(data: String?) {
        Log.d("MainJavascriptInterface","data = $data")
    }

    @JavascriptInterface
    open fun submitFromWeb(data: String, callbackId: String) {
        Log.d("chromium data", data + ", callbackId: " + callbackId + " " + Thread.currentThread().name)
        mWebView.sendResponse("submitFromWeb response", callbackId)
    }

}