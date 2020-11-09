package com.zhewen.kotlinsample.higherorder.lambda

/**
 * Lambda表达式简单演示使用
 *
 * 变量捕获与成员引用
 */
fun main(){

    val func: () -> String = fun(): String {
        return "Hello Kotlin"
    }

    //简化为lambda表达式
//    val func = {
//        "HelloKotlin"
//    }

    println(test(10, sum(3,5)))
    //调用lambda表达式实现
    println(test(10){num1, num2 -> num1 + num2 })

    val iop = fun Int.( other : Int) : Int = this + other
    println(2.iop(3))	//5

}

fun test(a : Int , b : Int) : Int{
    return a + b
}

fun sum(num1 : Int , num2 : Int) : Int{
    return num1 + num2
}

fun test(a:Int,b:(num1:Int,num2:Int) ->Int):Int{
    return a+ b.invoke(3,5)
}

class HTML {
    fun body() { //..... }
    }

    fun html(init: HTML.() -> Unit): HTML {
        val html = HTML()  // 创建接收者对象
        html.init()        // 将该接收者对象传给该 lambda
        return html
    }
}



