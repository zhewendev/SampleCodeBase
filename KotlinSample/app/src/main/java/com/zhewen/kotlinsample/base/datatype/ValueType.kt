package com.zhewen.kotlinsample.base.datatype

/**
 * 数值类型示例
 */
fun main(){
    //数值类型
    var a:Byte = 2
    var b:Short = 2
    var c:Int = 3
    var d:Long = 4L
    var e:Float = 5F
    var f:Double = 6.0

    //进制数
    var g = 0x0f    //十六进制
    var h = 0b0001  //二进制
    var i = 123     //十进制

    //数字分组
    var j:Long = 1_000_111_222L

    /**
     * 装箱与数值比较
     */
    println("--------装箱与数值比较----------")
    var numValue = 666
    var numValueBox: Int? = numValue    //装箱操作
    var numValue1 = 666
    println(numValue == numValueBox)
    println(numValueBox === numValue)   //根据数值类型的数值范围而定，可能相等，可能不等
    println(numValue===numValue1)


    /**
     * 类型转换
     */
    println("--------类型转换--------")
    println(a.toShort())        //显式转换
    println(a.toInt())
    println(a.toLong())
    println(a.toFloat())
    println(a.toDouble())

    println(a + c)  //隐式转换


    /**
     * 位运算，只用于Int和Long
     */
    println("----------位运算------------")
    println(c shl 2)
    println(c shr 1)
    println(c ushr 1)
    println(c and d.toInt())
    println(c or d.toInt())
    println(c xor d.toInt())
    println(c.inv())
}