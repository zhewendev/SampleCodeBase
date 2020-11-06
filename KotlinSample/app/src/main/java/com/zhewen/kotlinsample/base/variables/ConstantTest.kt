package com.zhewen.kotlinsample.base.variables

/**
 * 常量使用示例
 */

const val CLASS_NAME:String = "顶层声明"

object ConstantTest {
    const val CLASS_NAME:String = "object修饰的类中声明"
}

class ConstantTest1{
    companion object {
        const val CLASS_NAME = "companion object 中声明"
    }
}

fun main() {
    println(CLASS_NAME)
    println(ConstantTest.CLASS_NAME)
    println(ConstantTest1.CLASS_NAME)
}
