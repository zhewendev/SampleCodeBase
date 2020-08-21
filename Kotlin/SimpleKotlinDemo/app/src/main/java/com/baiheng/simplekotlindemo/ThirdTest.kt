package com.baiheng.simplekotlindemo
import kotlinx.coroutines.*


fun topLevelFunction() {

}
const val CONSt: String = "nihis"
val intArray = intArrayOf(1, 2, 3)
fun main() = runBlocking<Unit> { // start main coroutine
    GlobalScope.launch { // launch a new coroutine in background and continue
        delay(1000L)
        println("World!")
//        withContext()
    }
    println("Hello,") // main coroutine continues here immediately
    delay(2000L)      // delaying for 2 seconds to keep JVM alive
}


//open class ThirdTest : Test() {
//    val range: IntRange = 0..1000
//    val arr = arrayOf("1",2,3,4)
//    fun test() {
//        arr[0] = "syis"
//        val lists = list.toMutableList()
//        lists.add("")
//        intArray.map { i -> i + 1 }
//
//    }
//    var list: List<String> = listOf("ha","hi","he")
//
//
//}