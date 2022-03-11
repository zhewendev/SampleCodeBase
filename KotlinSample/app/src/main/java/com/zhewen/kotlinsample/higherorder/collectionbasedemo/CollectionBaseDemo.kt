package com.zhewen.kotlinsample.higherorder.collectionbasedemo

/**
 * 集合基础简单演示
 */
fun main(){
    val arr = arrayOf(1,2,3,4)
    var list1 = listOf(1,23,"kotlin",true)
    list1.slice()
    var list2 = listOf<String>("hello","kotlin")
    var list3 = arr.toList()
    var list4 = listOf(arr)
    var list6:List<Any> = list2

    var mutableList1 = mutableListOf<String>("Hello","kotlin")
    var mutableList2 = list4.toMutableList()
    var list5 = mutableList2.toList()
//    var mutableList3:MutableList<Any> = mutableList1  //报错

    //Set系列
    var set1 = setOf(1,2,3)
    var set2 = setOf<String>("Hello","kotlin")
    var set3:Set<Any> = set2
    var set4 = mutableSetOf("hello",1111)
    var set5 = hashSetOf("Hello","Hello","kotlin")

    //map系列
    var map1 = mapOf(1 to "11",2 to "22")
    var map2 = mutableMapOf("key1" to "value1","key2" to "value2")
    var map3 = hashMapOf("key1" to "value1", Pair("key2",11))
    map2.forEach{ (key, value) -> println("$key \t $value")
    }


    //惰性初始化
    val result = list3.asSequence().map {
        println(it)
        it * 2
    }.filter {
        it % 3 == 0
    }
    println(result.first())

}