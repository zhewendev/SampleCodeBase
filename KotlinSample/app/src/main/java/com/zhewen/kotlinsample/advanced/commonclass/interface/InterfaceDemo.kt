package com.zhewen.kotlinsample.advanced.commonclass.`interface`

/**
 * interface demo
 */
fun main() {

}

interface MyInterface {
    fun bar()
    fun foo()
}

class MyClass : MyInterface {
    override fun bar() {
        println("MyClass bar")
    }

    override fun foo() {
        println("MyClass foo")
    }
}

/**
 * 接口中属性与方法
 */
interface MyInterface1 {
    var name: String     //抽象属性
    var age: Int        //访问器实现，不可以有后备字段
        get() = 12
        set(value) {}

    fun show(){         //实现类无需重写该方法，若要重写也可以
        println("MyInterface1")
    }

}

interface MyInterface2{
    fun show(){
        println("MyInterface2")
    }
}



class MyClass1 : MyInterface1,MyInterface2{
    override var name: String
        get() = "MyClass1"
        set(value) {}

    override fun show() {
        super<MyInterface1>.show()
        super<MyInterface2>.show()
    }
}