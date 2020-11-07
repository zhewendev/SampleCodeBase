package com.zhewen.kotlinsample.base.controlflow

/**
 * control flow demo,contain if/while/when/do...while/for expression
 */
fun main() {
    //if expression
    println("--------if expression---------")
    var num1 = 11
    var num2 = 99
    println(if (num1 > num2) num1 else num2)
    var num3 = if (num1 > 10) {
        num1
    } else {
        num2
    }
    println(num3)

    //for expression
    println("------for expression------")
    for (i in 0 until 5) {
        print("$i\t")
    }
    println("\n")
    val array = intArrayOf(12, 13, 15, 14)
    for (element in array) {
        print("$element\t")
    }
    println("\n")

    for (element in "Kotlin") {
        print("$element\t")
    }

    for ((index, value) in array.withIndex()) {
        print("index=$index,value=$value \t")
    }

    //when expression
    println("--------when expression---------")
    var num4 = 44
    when (num4) {
        in 0..10 -> println("num4 in 0..10")
        in 10..100 -> println("num4 in 10..100")
        else -> println("num4 not in 0..100")
    }

    when("abc") {
        is String -> println("abc is a String")
        else -> println("abc is not a String")
    }

    var num5 = when(num4) {
        is Int -> 100
        else -> 0
    }
    println("num5 value is $num5")

    //while/do...while使用等同于Java

}