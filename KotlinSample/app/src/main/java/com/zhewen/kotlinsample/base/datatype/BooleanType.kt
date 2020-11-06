package com.zhewen.kotlinsample.base.datatype

/**
 * 布尔型数据\字符型示例
 */
fun main() {
    //布尔型
    println("--------布尔型------------")
    var boor: Boolean = true
    println(boor || true)
    println(boor && false)
    println(!boor)

    //字符型
    println("--------字符型----------")
    var c:Char = 'c'
    if (c.toInt() == 1) println(1) else println(c)  //c == 1 这样写会报错
    println("\t\\\' ")  //字符转义
}