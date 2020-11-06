package com.zhewen.kotlinsample.base.controlflow

/**
 * control flow demo,contain if/while/when/do...while/for expression
 */
fun main(){
    //if expression
    println("--------if expression---------")
    var num1 = 11
    var num2 = 99
    println(if (num1 > num2) num1 else num2)
    var num3 = if (num1 > 10) {
        num1
    }else{
        num2
    }
    println(num3)

    //for expression
    println("------for expression------")
    for (i in 0 until 5) {
        print("$i\t")
    }
    println("\n")
    val array = intArrayOf(12,13,15,14)
    for (element in array) {
        print("$element\t")
    }
    println("\n")

    for (element in "Kotlin") {
        print("$element\t")
    }
    array.withIndex()

}