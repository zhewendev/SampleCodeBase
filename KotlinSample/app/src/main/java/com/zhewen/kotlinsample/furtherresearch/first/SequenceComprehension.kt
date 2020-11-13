package com.zhewen.kotlinsample.furtherresearch.first

/**
 * sequence 解析
 */
fun main(){
    (0..6)
        .asSequence()
        .map {//map返回是Sequence<T>，故它属于中间操作
            println("map: $it")
            return@map it + 1
        }
        .filter {//filter返回是Sequence<T>，故它属于中间操作
            println("filter: $it")
            return@filter it % 2 == 0
        }.count {
            it < 6
        }.run {
            println("result = $this")
        }
}