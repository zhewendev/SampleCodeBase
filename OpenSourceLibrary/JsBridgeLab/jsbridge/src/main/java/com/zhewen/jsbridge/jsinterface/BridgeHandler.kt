package com.zhewen.jsbridge.jsinterface

/**
 * 消息处理的基类Handler
 */
interface BridgeHandler {
    fun handler(data:String?,callback: Callback)
}