package com.zhewen.jsbridgedemo;

import android.util.Log;
import android.webkit.JavascriptInterface;

import androidx.annotation.NonNull;

import com.zhewen.jsbridge.BaseJavascriptInterface;
import com.zhewen.jsbridge.BridgeHelper;
import com.zhewen.jsbridge.BridgeWebView;

/**
 * Created on 2019/7/10.
 * Author: bigwang
 * Description:
 */
public class MainJavascriptInterface extends BaseJavascriptInterface {


    public MainJavascriptInterface(@NonNull BridgeWebView webView) {
        super(webView);
    }

    public MainJavascriptInterface(BridgeHelper helper) {
        super(helper);
    }


    public String send(String data) {
        return "it is default response";
    }


    @JavascriptInterface
    public String submitFromWeb(String data, String callbackId) {
        Log.d("chromium data", data + ", callbackId: " + callbackId + " " + Thread.currentThread().getName());
//        mWebView.sendResponse("submitFromWeb response", callbackId);
        return "submitFromWeb response";
    }
}
