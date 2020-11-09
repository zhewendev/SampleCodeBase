package com.zhewen.kotlinsample.advanced.commonclass.abstract

/**
 * 抽象类简单演示
 */
fun main(){

    val testAbstarctA = TestAbstarctA()
    println(testAbstarctA.TAG)
    println(testAbstarctA.name)
    println(testAbstarctA.init())
    println(testAbstarctA.test())
}

abstract class Language{
    val TAG = this.javaClass.simpleName
    fun test() : Unit{

    }
    abstract var name : String           // 抽象属性
    abstract fun init()                  // 抽象方法
}

class TestAbstarctA : Language(){

    override var name: String
        get() = "Kotlin"
        set(value) {}

    override fun init() {
        println("我是$name")
    }
}