package com.zhewen.kotlinsample.furtherresearch.first

/**
 * Kotlin中选择定义函数属性的时机选择演示
 *
 */
fun main() {

}

class Person(var name: String, var lastName: String, var weight: Double) {
    val fullName
        get() = "$name $lastName"

    //函数，包含对象所有可以被执行的行为或动作,函数方法一般会间接修改对象状态
    fun run(){

    }
    fun walk(){

    }
}

class VideoPlayer{

    var mPlayer:String? = null
}