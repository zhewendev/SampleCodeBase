package com.zhewen.kotlinsample.higherorder.delegate

import kotlin.properties.Delegates

/**
 * Delegate 简单演示
 */
fun main(){

    //类委托
    val b = BaseImpl(12)
    Derived(b).print()
    Derived(b).printMessage()

    //属性委托
    val delegate = Delegate()
    delegate.lazyValue
    delegate.lazyValue
    delegate.name = "Java"
    delegate.name = "Kotlin"

    val site = Site(mapOf("name" to "google","url" to "www.google.com"))
    println(site.name)
    println(site.url)
}

/**
 * 类委托
 */
interface Base{
    fun print()
    fun printMessage()
}

class BaseImpl(val x:Int):Base{
    override fun print() {
        println(x)
    }

    override fun printMessage() {
        println(x)
    }
}

class Derived(b:Base):Base by b{    //类委托
    override fun printMessage() {
        println("Derived")
    }
}

class Delegate{
    val lazyValue:String by lazy {
        println("first computed")
        "Hello"
    }

    var name:String by Delegates.observable("name"){property, oldValue, newValue ->
        println("property = ${property.name},oldValue = $oldValue,newValue = $newValue")
    }
}

//把属性存储在映射中
class Site(val map:Map<String,Any?>){
    val name:String by map
    val url:String by map
}

