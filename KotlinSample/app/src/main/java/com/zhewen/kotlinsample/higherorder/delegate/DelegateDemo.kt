package com.zhewen.kotlinsample.higherorder.delegate

import android.content.Context
import android.content.SharedPreferences
import com.zhewen.kotlinsample.AppContext
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

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
    delegate.address = "HangZhouCity"
    println(delegate.address)
    delegate.test { "Hello Kotlin" }

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

    var address:String by Delegates.notNull()

    fun test(computed:() ->String){
        val tempProperty by lazy { computed.invoke() }
        println(tempProperty)
    }
}

//把属性存储在映射中
class Site(val map:Map<String,Any?>){
    val name:String by map
    val url:String by map
}

object Example{
    var name:String by Preference<String>("name")
    var age:Int by Preference<Int>(22)
}

class Preference<T>(val default:T):ReadWriteProperty<Any?,T>{

    private val prefs: SharedPreferences by lazy { AppContext.getSharedPreferences("default", Context.MODE_PRIVATE) }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        return putPreference(property.name,value)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(property.name)
    }

    private fun <T> findPreference(name: String): T = with(prefs) {
        val res: Any? = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        res as T
    }

    private fun <T> putPreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply()
    }
}
