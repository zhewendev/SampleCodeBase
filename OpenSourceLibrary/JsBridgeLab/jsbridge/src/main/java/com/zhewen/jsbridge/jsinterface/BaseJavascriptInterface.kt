package com.zhewen.jsbridge.jsinterface

import android.text.TextUtils
import android.util.Log
import android.webkit.JavascriptInterface

open class BaseJavascriptInterface(callbacks: HashMap<String, OnBridgeCallback>) {

    companion object {
        const val TAG = "BaseJavascriptInterface"
    }

    private var mCallbacks: HashMap<String, OnBridgeCallback> = callbacks

    @JavascriptInterface
    open fun send(data: String, callbackId: String): String? {
        Log.d("chromium", data + ", callbackId: " + callbackId + " " + Thread.currentThread().name)
        return "response from native"
    }

    @JavascriptInterface
    fun showToast(data:String?,callbackId:String?) {
        Log.d(TAG,"data = $data, callbackId = $callbackId")
        data?.let {
            toast(it)
        }
    }

    @JavascriptInterface
    fun accessPhoneInfo(data: String?,callbackId: String?) {
        Log.d(TAG,"data = $data, callbackId = $callbackId")
        phoneInfo(data)
    }

    @JavascriptInterface
    open fun response(data: String?, responseId: String?) {
        Log.d(TAG, data + ", responseId: " + responseId + " " + Thread.currentThread().name)
        if (!TextUtils.isEmpty(responseId)) {
            val function: OnBridgeCallback? = mCallbacks.remove(responseId)
            function?.onCallback(data)
        }
    }

    open fun toast(data:String) {

    }
    open fun phoneInfo(data: String?){

    }

}