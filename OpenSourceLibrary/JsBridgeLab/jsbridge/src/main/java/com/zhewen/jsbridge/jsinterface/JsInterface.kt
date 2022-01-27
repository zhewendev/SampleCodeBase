package com.zhewen.jsbridge.jsinterface

interface JsInterface {
    fun callJs(data:String?)

    fun callJs(data: String?,responseCallback: Callback?)
}