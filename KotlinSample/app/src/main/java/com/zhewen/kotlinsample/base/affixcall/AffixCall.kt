package com.zhewen.kotlinsample.base.affixcall

/**
 * 中缀调用简单示例
 */

infix fun Int.max(other:Int):Int = kotlin.math.max(this,other)


fun main(){
    val map = mapOf<String,String>("java" to "java","kotlin" to "kotlin","python" to "python" )
    val map1 = mapOf(Pair("java","java"), Pair("kotlin","kotlin"))
    map.forEach { (key, value) ->
        println("key = $key,value = $value")
    }

    println( 12 max 100)
}