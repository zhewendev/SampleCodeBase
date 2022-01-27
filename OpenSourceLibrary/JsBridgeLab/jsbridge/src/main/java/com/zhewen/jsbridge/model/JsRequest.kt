package com.zhewen.jsbridge.model

/**
 * 向Js侧发送消息的数据类
 */
data class JsRequest(
    var callbackId: String? = "",
    var data: String? = "",
    var handlerName: String? = ""
) {

}