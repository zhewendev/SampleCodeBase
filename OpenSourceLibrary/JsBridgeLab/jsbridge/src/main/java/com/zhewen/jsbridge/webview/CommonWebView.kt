package com.zhewen.jsbridge.webview

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView

open class CommonWebView:WebView,IWebView {
    constructor(context: Context, attrs: AttributeSet, defStyle:Int):super(context,attrs,defStyle)

    constructor(context: Context, attrs: AttributeSet):super(context,attrs)

    constructor(context: Context):super(context)

    override fun getContext(url: String) {
        TODO("Not yet implemented")
    }


}