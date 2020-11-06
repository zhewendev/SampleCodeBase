package com.zhewen.kotlinsample.base.datatype

/**
 * Array Type Demo
 */
fun main(){
    //原生数组 original array
    println("--------original array------")
    var arr1 = intArrayOf(1,2,3,4)
    var arr2 = IntArray(3)
    arr2[0] = 12
    var arr3 = IntArray(12){12}
    println(arr1.contentToString())

    //装箱数组 boxArray
    println("-----box Array-----")
    var array1 = arrayOf(2,3,5)
    var array2 = arrayOfNulls<String>(1)
    var array3 = Array<String>(2){
        (it*it).toString()
    }
    var array4 = emptyArray<Int>()
    var array5 = array1.plus(11)
    array5.fill(111,0,1)
    print(array5.contentToString())
    for (element in array5){
        print(element)
    }
    println("\n")
    array5.forEach {
        print("$it\t")
    }
    println("\n")
    for (i in array5.indices) {
        print("$array5[i]\t")
    }

}