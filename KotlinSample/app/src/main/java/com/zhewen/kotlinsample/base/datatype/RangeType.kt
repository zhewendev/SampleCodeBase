package com.zhewen.kotlinsample.base.datatype

/**
 * Range Type demo
 */
fun main(){
    var range = 1..100
    var range1 = 1.0f..100.0f
    var range2 = 1..100 step 2
    var range3 = 100 downTo 1 step 8
    var range4 = 1 until 100 step 2
    for (i in range3){
        print("$i\t")
    }
}