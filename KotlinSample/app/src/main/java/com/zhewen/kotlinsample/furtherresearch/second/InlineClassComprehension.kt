package com.zhewen.kotlinsample.furtherresearch.second

/**
 * 内联类解析示例
 */
fun main(){

    val period = InlinedInt(21).toMinutes()

}

inline class InlinedInt(val value:Int){
    fun toMinutes() = Minutes(value * 60)
}

inline class Minutes(val value:Int)