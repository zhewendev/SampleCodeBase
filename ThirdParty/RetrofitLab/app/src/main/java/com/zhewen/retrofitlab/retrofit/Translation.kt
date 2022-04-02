package com.zhewen.retrofitlab.retrofit

data class Translation(private val status:Int,
private val content:Content) {

    class Content {
        val from: String? = null
        val to: String? = null
        val vendor: String? = null
        val out: String? = null
        val errNo = 0
    }

    fun show() {
        println(status)
        println(content.from)
        println(content.to)
        println(content.vendor)
        println(content.out)
        println(content.errNo)
    }
}