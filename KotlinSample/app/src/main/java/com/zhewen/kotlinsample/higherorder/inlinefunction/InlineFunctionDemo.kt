package com.zhewen.kotlinsample.higherorder.inlinefunction

import java.lang.Character.getName
import kotlin.jvm.internal.Intrinsics

/**
 * 内联函数简单示例
 */
fun main(){
    println(printName("Name:", {name -> name }))
    println(printName({it},{it}))

    foo2()

//    inlineFunction({ println("calling inline functions")
//        return // compile time error
//    },{ println("next parameter in inline functions")})
}

inline fun printName(a: String, name: (str: String) -> String): String {
    var str = "$a${name("Czh")}"
    return str
}

/**
 * 禁止内联
 */
inline fun printName(arg0:(String) ->String,noinline arg1:(String)->String): String{
    return "${arg0("Hello")}${arg1("Kotlin")}"
}

/**
 * 非局部返回
 */
fun ordinaryFunction(block: () -> Unit) {
    println("hi!")
}
fun foo() {
    ordinaryFunction {
//        return // 错误：不能使 `foo` 在此处返回
    }
}

inline fun inlined(block: () -> Unit) {
    println("inlined")
    block.invoke()
    println("hi!")
}
fun foo2() {
    inlined {
        return // OK：该 lambda 表达式是内联的,退出包含他的函数
    }
    println("foo2")
}

/**
 * crossinline注解
 */
inline fun inlineFunction(crossinline myFun: () -> Unit, nxtFun: () -> Unit) {
    myFun()
    nxtFun()
    print("code inside inline function")
}