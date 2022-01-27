package com.zhewen.jsbridge.jsinterface

class DefaultHandler:BridgeHandler {

    companion object {
        const val TAG = "DefaultHandler"
    }

    override fun handler(data: String?, callback: Callback) {
        callback.onCallback(data)
    }
}