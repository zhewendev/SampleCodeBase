package com.zhewen.kotlinsample.base.stringskill

/**
 * String common skills
 * 简单演示部分String的常用方法
 */

fun main() {
    //字符串属性
    println("------字符串属性--------")
    val str:String = "HelloWorld"
    println("str.lenth = ${str.length}")
    println("str index range = ${str.indices}")
    println("str last index = ${str.lastIndex}")

    //字符串查找
    println("-------字符串查找-------")
    val str1 = "Hello Kotlin,I like you"
    println("str1 first character is ${str1.first()}")  //为空抛异常
    println("find str first o character = ${str1.find {
        it == 'o'
    }}")

    //字符串截取
    println(str1.substring(0,5))
    //字符串替换
    println(str1.replace('o','O',false))

    //其他方法
    println("str1 count = ${str1.count()}")
    println("str1 count = ${str1.count { it=='k' }}")
    println("str1 is empty or not? ${str1.isEmpty()}")
    println("str plus str1 = ${str.plus(str1)}")
    println("str1 reversed is ${str1.reversed()}" )



}