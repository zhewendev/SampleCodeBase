package com.zhewen.kotlinsample.advanced.commonclass.inheritance

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * inheritance class demo
 */
fun main(){

    println(Cat().name)

    println("Constructing Derived(\"hello\", \"world\")")
    val d = Derived("hello", "world")
}

open class Animal{
    open var name:String = "animal"
    open var age:Int = 12
}

class Cat: Animal() {
    override var name: String = ""
        get() = "cat"
}

/**
 * 实现类无主构造函数
 */
class MyView : View{

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)
}

/**
 * 实现类存在主构造函数
 */
class MyView1(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
    : View(context, attrs, defStyleAttr) {

    constructor(context: Context?) : this(context,null,0)

    constructor(context: Context?,attrs: AttributeSet?) : this(context,attrs,0)
}

/**
 * 函数重写，需要open修饰符
 */
open class Demo{
    fun test(){}   //这个函数没有用open修饰符修饰
}

class DemoTest : Demo(){

//    fun test(){}   //编辑器直接报红报错
//    override fun test(){}   //同样报错
}

/**
 * 函数初始化顺序
 */
open class Base(val name: String) {

    init { println("Initializing Base") }

    open val size: Int =
            name.length.also { println("Initializing size in Base: $it") }
}

class Derived(
        name: String,
        val lastName: String
) : Base(name.capitalize().also { println("Argument for Base: $it") }) {

    init { println("Initializing Derived") }

    override val size: Int =
            (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}