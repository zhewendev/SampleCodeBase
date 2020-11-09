package com.zhewen.kotlinsample.advanced.objectexpression

/**
 * 对象声明简单示例
 */
fun main(){
    DataProvider.registerDataProvider()
    DataProvider.allDataProvider()
    Site.DeskTop.showName()
    val instance = MyClass.create()

    val x = MyClass.Companion
    x.create()

    val f: Factory<MyClass1> = MyClass1
    f.create().test()
}

/**
 * 简单的对象声明，是个饿汉式单例
 */
object DataProvider{
    fun registerDataProvider(){
        println("registerDataProvider")
    }

    fun allDataProvider(){
        println("allDataProvider")
    }
}

class Site{
    var name = "Hello World"

    /**
     * 类内部对象声明
     */
    object DeskTop{
        var url = "www.google.com"
        fun showName(){
            println("desk legs $url")   //这里无法访问外部类的方法与变量
        }
    }
}

/**
 * 伴生对象,一个类里只能声明一个内部关联对象
 */
class MyClass private constructor(){
    companion object {
        fun create(): MyClass = MyClass()
    }
}

class Sample {
    companion object {
        init {
            //静态初始化
        }
    }
}

interface Factory<T>{
    fun create():T
}
class MyClass1{
     companion object :Factory<MyClass1>{
         override fun create(): MyClass1 = MyClass1()
     }

    fun test() {
        println("test")
    }
 }