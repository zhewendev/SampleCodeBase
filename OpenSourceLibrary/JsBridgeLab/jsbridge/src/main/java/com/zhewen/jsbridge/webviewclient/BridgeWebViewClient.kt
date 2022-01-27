package com.zhewen.jsbridge.webviewclient

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Message
import android.util.Log
import android.view.KeyEvent
import android.webkit.ClientCertRequest
import android.webkit.HttpAuthHandler
import android.webkit.RenderProcessGoneDetail
import android.webkit.SafeBrowsingResponse
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.zhewen.jsbridge.util.JAVA_SCRIPT
import com.zhewen.jsbridge.util.webViewLoadLocalJs

/**
 * 接管WebViewClient相关处理逻辑，如果要自定义WebViewClient，需要继承该类
 */
class BridgeWebViewClient(listener: OnLoadJSListener,client: WebViewClient): WebViewClient() {
    companion object {
        const val TAG = "BridgeWebViewClient"
    }

    private var mJSListener: OnLoadJSListener = listener
    private var mClient: WebViewClient = client//注意使用前记得初始化

    fun setWebViewClient(client: WebViewClient) {
        mClient = client
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        if(view != null && request != null && request.url != null) {
            view.loadUrl(request.url.authority!!)
        }
        return mClient.shouldOverrideUrlLoading(view, request)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        mClient.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        mClient.onPageFinished(view, url)
        mJSListener.onLoadStart()
        Log.d(TAG, "onPageFinished: ")
        if (view != null) {
            webViewLoadLocalJs(view, JAVA_SCRIPT)   //注入JsBridge文件
        }
        mJSListener.onLoadFinished()
    }

    override fun onLoadResource(view: WebView?, url: String?) {
        mClient.onLoadResource(view, url)
    }

    override fun onPageCommitVisible(view: WebView?, url: String?) {
        mClient.onPageCommitVisible(view, url)
    }

    override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
        return mClient.shouldInterceptRequest(view, request)
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        mClient.onReceivedError(view, request, error)
    }

    override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
        mClient.onReceivedHttpError(view, request, errorResponse)
    }

    override fun onFormResubmission(view: WebView?, dontResend: Message?, resend: Message?) {
        mClient.onFormResubmission(view,dontResend,resend)
    }

    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        mClient.doUpdateVisitedHistory(view, url, isReload)
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        mClient.onReceivedSslError(view, handler, error)
    }

    override fun onReceivedClientCertRequest(view: WebView?, request: ClientCertRequest?) {
        mClient.onReceivedClientCertRequest(view, request)
    }

    override fun onReceivedHttpAuthRequest(view: WebView?, handler: HttpAuthHandler?, host: String?, realm: String?) {
        mClient.onReceivedHttpAuthRequest(view, handler, host, realm)
    }

    override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
        return mClient.shouldOverrideKeyEvent(view, event)
    }

    override fun onUnhandledKeyEvent(view: WebView?, event: KeyEvent?) {
        mClient.onUnhandledKeyEvent(view, event)
    }

    override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
        mClient.onScaleChanged(view, oldScale, newScale)
    }

    override fun onReceivedLoginRequest(view: WebView?, realm: String?, account: String?, args: String?) {
        mClient.onReceivedLoginRequest(view, realm, account, args)
    }

    override fun onRenderProcessGone(view: WebView?, detail: RenderProcessGoneDetail?): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mClient.onRenderProcessGone(view, detail)
        } else super.onRenderProcessGone(view, detail)
    }

    override fun onSafeBrowsingHit(view: WebView?, request: WebResourceRequest?, threatType: Int, callback: SafeBrowsingResponse?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            mClient.onSafeBrowsingHit(view, request, threatType, callback)
        } else {
            super.onSafeBrowsingHit(view, request, threatType, callback)
        }
    }

    interface OnLoadJSListener {
        fun onLoadStart()
        fun onLoadFinished()
    }
}