package com.zhewen.kotlinsample.higherorder.lambda

/**
 * 高阶函数的简单示例
 */
fun main(){
    HigherOrderFunction().testRun1()
    val str = "kotlin"
    /**
     * with高阶函数
     */
    println(with(str) {
        println( "length = ${this.length}" )	//length = 6
        println( "first = ${first()}")	//first = k
        println( "last = ${last()}" )	//last = n
    })

    /**
     * T.apply()高阶函数
     */
    val str1 = "Hello Kotlin"
    println(str1.also {
        println("length = ${it.length}")
        it.replace("Hello","java",false)
    })
    println(str1)
}

class HigherOrderFunction{
    fun testRun1(){
        val str = "kotlin"

        /**
         * run高阶函数
         */
        println(run{
            val str = "java"   // 和上面的变量不会冲突
            println("str = $str")	//str = java
        })

        println("str = $str")	//str = kotlin
    }
}