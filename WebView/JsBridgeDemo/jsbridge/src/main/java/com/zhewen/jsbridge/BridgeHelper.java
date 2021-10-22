package com.zhewen.jsbridge;

import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.webkit.ValueCallback;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * JsBridge辅助类，帮助集成JsBridge功能
 */
public class BridgeHelper implements WebViewJavascriptBridge, WebViewClientProxy.OnLoadJSListener {

    private HashMap<String,BridgeHandler> mMessageHandler = new HashMap<>();
    private HashMap<String,CallBackFunction> mCallbacks = new HashMap<>();

    private BridgeHandler mDefaultHandler = (data, function) -> {
        if(function != null){
            function.onCallBack("DefaultHandler response data","");
        }
    };
    private List<Object> mMessages = new ArrayList<>();
    private long mUniqueId = 0;
    private boolean mJSLoaded = false;
    private Gson mGson = new Gson();
    private IWebView mWebView;

    public BridgeHelper(IWebView mWebView) {
        this.mWebView = mWebView;
        mWebView.addJavascriptInterface(new BaseJavascriptInterface(this),"android");
        BridgeWebViewClient client = new BridgeWebViewClient() {
            @Override
            public void onBridgeLoadStart() {
                mJSLoaded = false;
            }

            @Override
            public void onBridgeLoadFinished() {
                mJSLoaded = true;
                if (mMessages != null) {
                    for (Object message: mMessages) {
                        dispatchMessage(message);
                    }
                    mMessages = null;
                }
            }
        };
        mWebView.setWebViewClient(client);
    }

    final HashMap<String,CallBackFunction> getResponseCallbacks() {
        return mCallbacks;
    }

    final HashMap<String,BridgeHandler> getMessageHandler() {
        return mMessageHandler;
    }

    final BridgeHandler getDefaultHandler() {
        return mDefaultHandler;
    }

    public boolean isJSLoaded() {
        return mJSLoaded;
    }

    public void setGson(Gson gson) {
        mGson = gson;
    }

    // TODO: 2021/10/21 为什么这里也进行消息分发
    @Override
    public void onLoadStart() {
        mJSLoaded = false;
    }

    @Override
    public void onLoadFinished() {
        mJSLoaded = true;
        if (mMessages != null) {
            for (Object message : mMessages) {
                dispatchMessage(message);
            }
            mMessages = null;
        }
    }

    @Override
    public void callJs(Object data) {
        callJs(data,(CallBackFunction) null);
    }

    @Override
    public void callJs(Object data, CallBackFunction responseCallback) {
        doCall(null,data,responseCallback);
    }

    /**
     * 调用javascript处理程序注册
     * @param handlerName
     * @param data
     * @param callback
     */
    public void callHandler(String handlerName,String data,CallBackFunction callback) {
        doCall(handlerName,data,callback);
    }

    /**
     * 注册处理程序，便于调用javascript处理
     * @param handlerName
     * @param handler
     */
    public void registerHandler(String handlerName,BridgeHandler handler) {
        if (handler != null) {
            mMessageHandler.put(handlerName,handler);
        }
    }

    public void unregisterHandler(String handlerName) {
        if (handlerName != null) {
            mMessageHandler.remove(handlerName);
        }
    }

    @Override
    public void callJs(String function, Object... values) {

        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            String jsCommand = String.format(function, values);
            jsCommand = String.format(BridgeUtil.JAVASCRIPT_STR, jsCommand);
            mWebView.evaluateJavascript(jsCommand, null);
        }
    }

    @Override
    public void callJs(String function, ValueCallback<String> callback, Object... values) {

        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            String jsCommand = String.format(function, values);
            jsCommand = String.format(BridgeUtil.JAVASCRIPT_STR, jsCommand);
            mWebView.evaluateJavascript(jsCommand, callback);
        }
    }

    /**
     * 保存message到消息队列
     * @param handlerName
     * @param data
     * @param responseCallback
     */
    private void doCall(String handlerName,Object data,CallBackFunction responseCallback) {
        if (!(data instanceof String) && mGson == null) {
            return;
        }
        JSRequest request = new JSRequest();
        if (data != null) {
            request.data = data instanceof String ? (String) data : mGson.toJson(data);
        }
        if (responseCallback != null) {
            String callbackId = String.format(BridgeUtil.CALLBACK_ID_FORMAT, (++mUniqueId) + (BridgeUtil.UNDERLINE_STR + SystemClock.currentThreadTimeMillis()));
            mCallbacks.put(callbackId, responseCallback);
            request.callbackId = callbackId;
        }
        if (!TextUtils.isEmpty(handlerName)) {
            request.handlerName = handlerName;
        }
        queueMessage(request);
    }

    /**
     * list<message> != null 添加到消息集合否则分发消息
     *
     * @param message Message
     */
    private void queueMessage(Object message) {
        if (mMessages != null) {
            mMessages.add(message);
        } else {
            dispatchMessage(message);
        }
    }

    /**
     * 分发message到Web端 必须在主线程才分发成功
     *
     * @param message Message
     */
    private void dispatchMessage(Object message) {
        if (mGson == null) {
            return;
        }
        String messageJson = mGson.toJson(message);
        //escape special characters for json string  为json字符串转义特殊字符
        messageJson = messageJson.replaceAll("(\\\\)([^utrn])", "\\\\\\\\$1$2");
        messageJson = messageJson.replaceAll("(?<=[^\\\\])(\")", "\\\\\"");
        messageJson = messageJson.replaceAll("(?<=[^\\\\])(\')", "\\\\\'");
        try {
            messageJson = messageJson.replaceAll("%7B", URLEncoder.encode("%7B","UTF-8"));
            messageJson = messageJson.replaceAll("%7D", URLEncoder.encode("%7D","UTF-8"));
            messageJson = messageJson.replaceAll("%22", URLEncoder.encode("%22","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String javascriptCommand = String.format(BridgeUtil.JS_HANDLE_MESSAGE_FROM_JAVA, messageJson);
        // 必须要找主线程才会将数据传递出去 --- 划重点
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            mWebView.evaluateJavascript(javascriptCommand, null);
        }
    }

    public void sendResponse(Object data, String callbackId) {
        if (!(data instanceof String) && mGson == null) {
            return;
        }
        if (!TextUtils.isEmpty(callbackId)) {
            final JSResponse response = new JSResponse();
            response.responseId = callbackId;
            response.responseData = data instanceof String ? (String) data : mGson.toJson(data);
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                dispatchMessage(response);
            } else {
                mWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        dispatchMessage(response);
                    }
                });
            }

        }
    }

    public void destroy() {
        mCallbacks.clear();
        mMessageHandler.clear();
    }
}
