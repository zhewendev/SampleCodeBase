package com.zhewen.kotlinsample.furtherresearchfirst.choosefucorprop

/**
 * 函数式API调用部分示例，具体参看文档或源码与官方文档
 */
fun main(){

    /**
     * Slice操作符，可以取集合中一部分元素或者某个元素，最后组合成一个新元素
     */
    val numberList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val newNumberList1 = numberList.slice(IntRange(3, 6))
    println("----------slice start----------")
    newNumberList1.forEach {
        print("$it\t")
    }
    println()
    val newNumberList2 = numberList.slice(listOf(1, 3, 7))
    print("slice by iterator index: ")
    newNumberList2.forEach {
        print("$it\t")	//2 4 8
    }
    println()

    /**
     * filter：根据用户定义的条件筛选集合中的数据，由此产生一个新的集合，该集合是原集合的子集。
     * filterTo:从多个集合筛选出符合条件的元素，并最终用一个集合进行收集从每个集合筛选出的元素
     */
    val numberListFilter = numberList.filter {
        it % 2 == 0
    }
    println("-------------filter--------------")
    numberListFilter.forEach { print("$it\t") }
    println()

    val numberList1 = listOf(23, 65, 14, 57, 99, 123, 26, 15, 88, 37, 56)
    val numberList2 = listOf(13, 55, 24, 67, 93, 137, 216, 115, 828, 317, 16)
    val numberList3 = listOf(20, 45, 19, 7, 9, 3, 26, 5, 38, 75, 46)
    val numberListFilterTo = mutableListOf<Int>().apply {
        numberList1.filterTo(this) {
            it % 2 == 0
        }
        numberList2.filterTo(this) {
            it % 2 == 0
        }
        numberList3.filterTo(this) {
            it % 2 == 0
        }
    }
    //从三个集合中筛选出偶数集合
    println("--------filterTo------------")
    numberListFilterTo.forEach{
        print("$it\t")
    }
    println()

    /**
     * filterIndexed和filterIndexedTo:与filter一致，只是筛选条件多了个index
     */
    println("----------filterIndexed-----------")
    val numberListFilterIndexed = numberList.filterIndexed{index, i ->
        index < 5 && i % 2 == 0
    }
    numberListFilterIndexed.forEach { print("$it\t") }
    println()




}