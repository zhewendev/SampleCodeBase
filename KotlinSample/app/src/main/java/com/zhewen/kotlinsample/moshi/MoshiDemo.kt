package com.zhewen.kotlinsample.moshi

import com.squareup.moshi.JsonClass

fun main() {
    val json = """{}"""
    val p1 = gson.fromJson(json, DefaultAll::class.java)
    println("gson parse json: $p1")
    val p2 = moshi.adapter(DefaultAll::class.java).fromJson(json)
    println("moshi parse json: $p2")
}

@JsonClass(generateAdapter = true)
data class DefaultAll(
    val name: String = "me",
    val age: Int = 17
)
