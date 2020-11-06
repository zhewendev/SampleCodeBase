package com.zhewen.kotlinsample.base.datatype

/**
 * 字符串类型示例
 */
fun main(){
    println("---------String----------")
    var str = "kotlin"
    for (element in str) {
        print("\t$element")
    }
    println("\n")
    println(str[2])
    println("""
        hello kotlin
        you are so great!
    """.trimIndent())
    println("""
        > I like java
        > I like Kotlin
        > I like python
    """.trimMargin(">").trimIndent())
}