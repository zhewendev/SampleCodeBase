package com.zhewen.jsbridge.webview

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.os.SystemClock
import android.text.TextUtils
import android.util.AttributeSet
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.collection.ArrayMap
import com.google.gson.Gson
import com.zhewen.jsbridge.jsinterface.BridgeHandler
import com.zhewen.jsbridge.jsinterface.Callback
import com.zhewen.jsbridge.jsinterface.OnBridgeCallback
import com.zhewen.jsbridge.model.JsRequest
import com.zhewen.jsbridge.model.JsResponse
import com.zhewen.jsbridge.util.CALLBACK_ID_FORMAT
import com.zhewen.jsbridge.util.JS_HANDLE_MESSAGE_FROM_JAVA
import com.zhewen.jsbridge.util.UNDERLINE_STR
import com.zhewen.jsbridge.webviewclient.BridgeWebViewClient
import java.net.URLEncoder

/**
 * 集成了JsBridge能力的WebView
 */
class BridgeWebView:WebView,BridgeWebViewClient.OnLoadJSListener {

    companion object {
        const val URL_MAX_CHARACTER_NUM = 2097152
    }
    val responseCallbacks = HashMap<String, Callback>() //js发送消息，native侧回调
    val messageHandlers = HashMap<String, BridgeHandler>()
    private var mMessages:ArrayList<Any>? = ArrayList()
    private val mCallbacks: HashMap<String, OnBridgeCallback> = HashMap()  //native发送消息，Js侧回调
    private lateinit var mClient: BridgeWebViewClient
    private var mUniqueId: Long = 0L
    private var mJSLoaded = false
    private lateinit var mGson: Gson

    constructor(context: Context,attrs:AttributeSet,defStyle:Int):super(context,attrs,defStyle) {
        init()
    }

    constructor(context: Context,attrs:AttributeSet):super(context,attrs) {
        init()
    }

    constructor(context: Context):super(context) {
        init()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        clearCache(true)
        settings.useWideViewPort = true
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        setWebContentsDebuggingEnabled(true)
        mClient = BridgeWebViewClient(this,WebViewClient())
        webViewClient = mClient
    }

    fun setGson(gson: Gson) {
        mGson = gson
    }

    fun isJsLoaded():Boolean = mJSLoaded

    override fun onLoadStart() {
        mJSLoaded = false
    }

    /**
     * Js文件完成注入，进行消息分发
     */
    override fun onLoadFinished() {
        mJSLoaded = true
        if (mMessages != null) {
            for (message in mMessages!!) {
                dispatchMessage(message)
            }
        }
        mMessages = null
    }

    /**
     * 向Js发送请求，单向通信无回调
     */
    fun sendToWeb(data:Any) {
        sendToWeb(data,null)
    }

    /**
     * 向Js发送请求，双向通信有回调
     */
    fun sendToWeb(data:Any,responseCallback:OnBridgeCallback?) {
        doSend(null,data,responseCallback)
    }

    /**
     * 调用javascript处理程序注册
     * todo
     */
    fun callHandler(handlerName: String,data:String,onBridgeCallback: OnBridgeCallback) {
        doSend(handlerName,data,onBridgeCallback)
    }


    /**
     * 保存message到消息队列
     *
     * @param handlerName      handlerName
     * @param data             data
     * @param responseCallback OnBridgeCallback
     */
    private fun doSend(handlerName: String?, data: Any?, responseCallback: OnBridgeCallback?) {
        val request = JsRequest()
        if (data != null) {
            request.data = if (data is String) data else mGson.toJson(data)
        }
        if (responseCallback != null) {
            val callbackId: String = java.lang.String.format(
                CALLBACK_ID_FORMAT,
                (++mUniqueId).toString() + (UNDERLINE_STR + SystemClock.currentThreadTimeMillis())
            )
            mCallbacks[callbackId] = responseCallback
            request.callbackId = callbackId
        }
        if (!TextUtils.isEmpty(handlerName)) {
            request.handlerName = handlerName
        }
        queueMessage(request)
    }

    private fun queueMessage(message: Any) {
        mMessages?.add(message) ?: dispatchMessage(message)
    }

    /**
     * 分发message，必须在主线程才能分发成功
     */
    private fun dispatchMessage(message: Any) {
        var messageJson = mGson.toJson(message)
        //escape special characters for json string  为json字符串转义特殊字符
        messageJson = messageJson.replace("(\\\\)([^utrn])".toRegex(), "\\\\\\\\$1$2")
        messageJson = messageJson.replace("(?<=[^\\\\])(\")".toRegex(), "\\\\\"")
        messageJson = messageJson.replace("(?<=[^\\\\])(\')".toRegex(), "\\\\\'")
        messageJson = messageJson.replace("%7B".toRegex(), URLEncoder.encode("%7B"))
        messageJson = messageJson.replace("%7D".toRegex(), URLEncoder.encode("%7D"))
        messageJson = messageJson.replace("%22".toRegex(), URLEncoder.encode("%22"))
        val javascriptCommand: String = java.lang.String.format(JS_HANDLE_MESSAGE_FROM_JAVA, messageJson)
        // 必须要找主线程才会将数据传递出去 --- 划重点
        if (Thread.currentThread() === Looper.getMainLooper().thread) {
            evaluateJavascript(javascriptCommand, null)
        }
    }

    fun sendResponse(data: Any?, callbackId: String) {
        if (data !is String) {
            return
        }
        if (!TextUtils.isEmpty(callbackId)) {
            val response = JsResponse()
            response.responseId = callbackId
            response.responseData = data
            if (Thread.currentThread() === Looper.getMainLooper().thread) {
                dispatchMessage(response)
            } else {
                post { dispatchMessage(response) }
            }
        }
    }

    fun getCallbacks(): HashMap<String, OnBridgeCallback> {
        return mCallbacks
    }


    override fun destroy() {
        super.destroy()
        mCallbacks.clear()
    }
}