package com.zhewen.jsbridge.util

const val JAVA_SCRIPT = "WebViewJsBridge.js"
const val JS_HANDLE_MESSAGE_FROM_JAVA = "javascript:WebViewJavascriptBridge" + "._handleMessageFromNative('%s');"
const val JS_FETCH_QUEUE_FROM_JAVA = "javascript:WebViewJavascriptBridge._fetchQueue();"
const val CALLBACK_ID_FORMAT = "JAVA_CB_%s"
const val EMPTY_STR = ""
const val UNDERLINE_STR = "_"
const val SPLIT_MARK = "/"
const val JS_OVERRIDE_SCHEMA = "jsBridge://"
const val JS_RETURN_DATA = JS_OVERRIDE_SCHEMA + "return/"
const val JS_FETCH_QUEUE = JS_RETURN_DATA + "_fetchQueue/"