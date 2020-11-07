package com.zhewen.kotlinsample.base.function

import kotlin.random.Random

/**
 * function skill demo
 */

fun funcTest() {  //顶级函数声明
    println("this is top-function")
}

class FunctionTest1 {
    fun funcTest1(): Int {    //带接收者的函数
        println("this is function with Receiver")
        return 10
    }
}

fun funcTest2(block: () -> Int): Int {
    return block.invoke() + 11
}

fun funcTest3(): Int {
    return (0..100).random()
}

fun funcTest4(arg0: Int, arg1: Int): Int {
    return arg0 + arg1
}

fun funcTest5(int: Int, block: (Int, Int) -> Int): Int {
    return int + block.invoke(12, 13)
}

//测试函数参数
fun funcParameterTest1(str: String?) {
    println("String 可空变量值为$str")
}

fun funcParameterTest2(arg0: Int, arg1: Int = 10): Int {
    return arg0 + arg1
}

fun funcParameterTest3(arg0: Int = 10, arg1: Int): Int {
    return arg0 + arg1
}

@JvmOverloads   //自动生成多个重载方法供java调用
fun funcParameterTest4(arg0: Int = 1, arg1: Int = 2, arg3: Int = 3) {
}

fun funcParameterTest5(vararg int: Int): Int {
    var total = 0
    for (i in int) {
        total += i
    }
    return total
}


//本地函数
fun funcLocalFuncTest(arg0:Int,arg1:Int,arg2:Int){
    fun isPositiveNum(arg0:Int) :Boolean{
        return arg0 > 0
    }
    if (isPositiveNum(arg0)||isPositiveNum(arg1)||isPositiveNum(arg2)){
        println("三个参数至少有一个是正数")
    }


}

fun main() {
    funcTest()
    FunctionTest1().funcTest1()

    //函数引用
    println("--------函数引用---------")
    var block = ::funcTest2
    var block1 = ::funcTest3
    var block3 = FunctionTest1::funcTest1
    println(block)
    println(block {
        12
    })
    println(block(block1))
//    println(block(block3))      //报错

    val addVariant = ::funcTest4
    println(funcTest5(12, addVariant))
    println(funcTest5(12) { arg0: Int, arg1: Int -> arg0 + arg1 })

    //函数参数
    println("------函数参数----------")
    println(funcParameterTest1(null))
    println(funcParameterTest2(10))
    println(funcParameterTest3(arg1 = 10))

    //可变参数
    println(funcParameterTest5(1, 2, 3, 4, 5))

    //本地函数
    funcLocalFuncTest(-1,11,100)
}