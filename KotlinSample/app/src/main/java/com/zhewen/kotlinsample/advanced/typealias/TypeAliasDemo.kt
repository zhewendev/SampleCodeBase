package com.zhewen.kotlinsample.advanced.`typealias`

/**
 * 类型别名简单示例
 */
fun main(){
    First().show()
    NestedA().show()
    val f:(Int) ->Boolean = {it>0}
    println(First().foo(f))

}

typealias First = TypeAliasDemoTestFirst    //类型别名，声明在顶部
typealias NestedA = TypeAliasDemoTestFirst.A
typealias Predicate<T> = (T) ->Boolean

// 测试类
class TypeAliasDemoTestFirst{
    fun show(){
        println("name : ${this.javaClass.simpleName}")
    }

    class A{
        fun show(){
            println("name:${this.javaClass.simpleName}")
        }
    }

    fun foo(p:Predicate<Int>) = p(42)
}