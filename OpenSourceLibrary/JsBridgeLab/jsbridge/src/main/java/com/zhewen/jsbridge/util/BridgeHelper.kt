package com.zhewen.jsbridge.util

import android.os.Looper
import android.os.SystemClock
import android.util.Log
import com.zhewen.jsbridge.jsinterface.BridgeHandler
import com.zhewen.jsbridge.jsinterface.Callback
import com.zhewen.jsbridge.jsinterface.DefaultHandler
import com.zhewen.jsbridge.jsinterface.JsInterface
import com.zhewen.jsbridge.model.Message
import com.zhewen.jsbridge.webview.CommonWebView
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder

/**
 * JsBridge辅助类，帮助集成JsBridge功能.
 */
class BridgeHelper(val webView: CommonWebView):JsInterface {
    companion object {
        const val TAG = "BridgeHelper"
        const val BRIDGE_JS = "WebViewJsBridge.js"
    }

    private val responseCallbacks = HashMap<String, Callback>()
    private val messageHandlers = HashMap<String, BridgeHandler>()
    var defaultHandler = DefaultHandler()
    var startupMessage:ArrayList<Message>? = ArrayList()
    private var uniqueId: Long = 0L

    /**
     * 获取到Callback data，执行调用并从数据集中移除
     */
    private fun handlerReturnData(url:String) {
        val functionName: String = getFunctionFromReturnUrl(url) ?: return
        val f: Callback = responseCallbacks[functionName]?:return
        val data: String? = getDataFromReturnUrl(url)
        f.onCallback(data)
        responseCallbacks.remove(functionName)
    }

    override fun callJs(data: String?) {
        callJs(data,null)
    }

    override fun callJs(data: String?, responseCallback: Callback?) {
        doSend(null,data,responseCallback)
    }

    fun callHandler(handlerName: String, data: String?, callBack: Callback?) {
        doSend(handlerName, data, callBack)
    }

    private fun doSend(handlerName: String?, data: String?, responseCallback: Callback?) {
        val message = Message()
        data?.let { message.data = it }
        responseCallback?.let {
            val callbackStr: String = java.lang.String.format(
                CALLBACK_ID_FORMAT,
                (++uniqueId).toString() + (UNDERLINE_STR + SystemClock.currentThreadTimeMillis())
            )
            responseCallbacks[callbackStr] = responseCallback
            message.callbackId = callbackStr
        }
        handlerName?.let{message.handlerName = it}
    }

    private fun queueMessage(m: Message) {
        startupMessage?.add(m) ?: dispatchMessage(m)
    }


    private fun dispatchMessage(m: Message) {
        var messageJson = m.toJson()
        //escape special characters for json string  为json字符串转义特殊字符
        messageJson = messageJson!!.replace("(\\\\)([^utrn])".toRegex(), "\\\\\\\\$1$2")
        messageJson = messageJson.replace("(?<=[^\\\\])(\")".toRegex(), "\\\\\"")
        messageJson = messageJson.replace("(?<=[^\\\\])(\')".toRegex(), "\\\\\'")
        messageJson = messageJson.replace("%7B".toRegex(), URLEncoder.encode("%7B"))
        messageJson = messageJson.replace("%7D".toRegex(), URLEncoder.encode("%7D"))
        messageJson = messageJson.replace("%22".toRegex(), URLEncoder.encode("%22"))
        val javascriptCommand: String = java.lang.String.format(JS_HANDLE_MESSAGE_FROM_JAVA, messageJson)
        // 必须要找主线程才会将数据传递出去 --- 划重点
        if (Thread.currentThread() === Looper.getMainLooper().thread) {
            this.loadUrl(javascriptCommand)
        }
    }

    private fun loadUrl(jsUrl: String, returnCallback: Callback) {
        this.loadUrl(jsUrl)
        responseCallbacks[parseFunctionName(jsUrl)] = returnCallback
    }

    private fun loadUrl(jsUrl: String) {
        webView.loadUrl(jsUrl)
    }

    fun registerHandler(handlerName: String?, handler: BridgeHandler?) {
        if (handler != null && handlerName != null) {
            messageHandlers[handlerName] = handler
        }
    }

    fun unregisterHandler(handlerName: String?) {
        if (handlerName != null) {
            messageHandlers.remove(handlerName)
        }
    }

    fun onPageFinished() {
        webViewLoadLocalJs(webView, BRIDGE_JS)
        if (startupMessage != null) {
            for (m in startupMessage!!) {
                dispatchMessage(m)
            }
            startupMessage = null
        }
    }

    fun shouldOverrideUrlLoading(url: String): Boolean {
        var webUrl = url
        try {
            val replacedUrl = webUrl.replace("%(?![0-9a-fA-F]{2})".toRegex(), "%25").replace("\\+".toRegex(), "%2B")
            webUrl = URLDecoder.decode(replacedUrl, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            Log.w(TAG, e)
        }
        if (webUrl.startsWith(JS_RETURN_DATA)) { // 如果是返回数据
            handlerReturnData(url)
            return true
        } else if (webUrl.startsWith(JS_OVERRIDE_SCHEMA)) { //
            flushMessageQueue()
            return true
        }
        return false
    }

    private fun flushMessageQueue() {
        if (Thread.currentThread() !== Looper.getMainLooper().thread) {
            return
        }
        loadUrl(JS_FETCH_QUEUE_FROM_JAVA,object :Callback{
            override fun onCallback(data: String?) {
                TODO("Not yet implemented")
            }
        })
    }
}