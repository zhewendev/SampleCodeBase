package com.zhewen.jsbridge.util

import android.content.Context
import android.util.Log
import android.webkit.WebView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

private const val TAG = "BridgeUtil"

fun webViewLoadLocalJs(view: WebView, path: String?) {
    val jsContent: String? = assetFile2Str(view.context, path)
    Log.d(TAG, "webViewLoadLocalJs: $jsContent")
    view.loadUrl("javascript:$jsContent")
}

fun assetFile2Str(c: Context, urlStr: String?): String? {
    var `in`: InputStream? = null
    try {
        `in` = c.assets.open(urlStr!!)
        val bufferedReader = BufferedReader(InputStreamReader(`in`))
        var line: String?
        val sb = StringBuilder()
        do {
            line = bufferedReader.readLine()
            if (line != null && !line.matches(Regex("^\\s*\\/\\/.*"))) {
                sb.append(line)
            }
        } while (line != null)
        bufferedReader.close()
        `in`.close()
        return sb.toString()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        if (`in` != null) {
            try {
                `in`.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    return null
}

fun getFunctionFromReturnUrl(url: String): String? {
    val temp: String = url.replace(JS_RETURN_DATA, EMPTY_STR)
    val functionAndData: Array<String> = temp.split(SPLIT_MARK).toTypedArray()
    return if (functionAndData.isNotEmpty()) {
        functionAndData[0]
    } else null
}

fun getDataFromReturnUrl(url: String): String? {
    if (url.startsWith(JS_FETCH_QUEUE)) {
        return url.replace(JS_FETCH_QUEUE, EMPTY_STR)
    }
    val temp: String = url.replace(JS_RETURN_DATA, EMPTY_STR)
    val functionAndData: Array<String> = temp.split(SPLIT_MARK).toTypedArray()
    if (functionAndData.size >= 2) {
        val sb = java.lang.StringBuilder()
        for (i in 1 until functionAndData.size) {
            sb.append(functionAndData[i])
        }
        return sb.toString()
    }
    return null
}

fun parseFunctionName(jsUrl: String): String {
    return jsUrl.replace("javascript:WebViewJavascriptBridge.", "").replace("\\(.*\\);".toRegex(), "")
}