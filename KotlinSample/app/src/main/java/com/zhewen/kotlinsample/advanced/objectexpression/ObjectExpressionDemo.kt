package com.zhewen.kotlinsample.advanced.objectexpression

/**
 * 对象表达式简单演示
 */
fun main(){
    /**
     * 对象表达式
     */
    val a: Any = object : Person(), Move {
        override fun walk() {
            println("walk")
        }
        override fun play() {
            println("play")
        }
    }

    if (a is Person) a.play()
    if (a is Move) a.walk()

    //越过类的定义，直接得到一个对象
    val adHoc = object {
        var x:Int = 12
        var y:Int = 12
    }
    println(adHoc.x + adHoc.y)
}

abstract class Person {
    abstract fun play()
}
interface Move {
    fun walk()
}


class C{
    // 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    // 公有函数，所以其返回类型是 Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x        // 没问题
//        val x2 = publicFoo().x  // 错误：未能解析的引用“x”
    }
}
