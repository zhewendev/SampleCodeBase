package com.zhewen.retrofitlab.retrofit

data class Info(
    val name: String,
    val url: String,
    val picUrl: String
)

class PostBodyBean(private val key: String? = null,
        private val num:Int = 0,
        private val isTrue:Boolean = false) {
}