package com.zhewen.jsbridge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

@SuppressLint("SetJavaScriptEnabled")
public class BridgeWebView extends WebView implements WebViewJavascriptBridge,IWebView {


    private BridgeHelper mBridgeHelper;

    public BridgeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BridgeWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public BridgeWebView(Context context) {
        super(context);
        init();
    }

    private void init() {
        clearCache(true);
        WebSettings webSettings = getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        mBridgeHelper = new BridgeHelper(this);
    }

    @NonNull
    final BridgeHelper getBridgeHelper() {
        return mBridgeHelper;
    }

    public void setGson(Gson gson) {
        mBridgeHelper.setGson(gson);
    }

    public void registerHandler(String handlerName,BridgeHandler handler) {
        mBridgeHelper.registerHandler(handlerName,handler);
    }

    public void unregisterHandler(String handlerName) {
        mBridgeHelper.unregisterHandler(handlerName);
    }

    public void callHandler(String handlerName, String data, CallBackFunction callBack) {
        mBridgeHelper.callHandler(handlerName, data, callBack);
    }

    @Override
    public void callJs(Object data) {
        mBridgeHelper.callJs(data);
    }

    @Override
    public void callJs(Object data, CallBackFunction responseCallback) {
        mBridgeHelper.callJs(data,responseCallback);
    }

    @Override
    public void callJs(String function, Object... values) {
        mBridgeHelper.callJs(function,values);
    }

    @Override
    public void callJs(String function, ValueCallback<String> callback, Object... values) {
        mBridgeHelper.callJs(function,callback,values);
    }
}
