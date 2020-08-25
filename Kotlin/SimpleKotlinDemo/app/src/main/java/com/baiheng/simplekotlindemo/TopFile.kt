package com.baiheng.simplekotlindemo

//利用to函数初始化一个map
fun main(args: Array<String>) {
    val map = mapOf(1.to("A"), 2.to("B"), 3.to("C"))
    map.forEach { key, value ->
        println("key: $key   value:$value")
    }
}

//利用中缀调用into判断元素是否在集合中
fun main(args: Array<String>) {
    val list = listOf(1, 3, 5, 7, 9)
    val element = 2
    if (element into list) {//中缀调用，这样的写法，会更加接近我们自然语言的表达，更容易理解
        println("element: $element is into list")
    } else {
        println("element: $element is not into list")
    }
}

private infix fun Int.into(list: List<Int>): Boolean {

}
