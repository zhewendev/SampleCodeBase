package com.zhewen.jsbridge;


import android.webkit.ValueCallback;

public interface WebViewJavascriptBridge {
	
	void callJs(Object data);

	void callJs(Object data, CallBackFunction responseCallback);

	void callJs(String function, Object... values);

	void callJs(String function, ValueCallback<String> callback, Object... values);
}
