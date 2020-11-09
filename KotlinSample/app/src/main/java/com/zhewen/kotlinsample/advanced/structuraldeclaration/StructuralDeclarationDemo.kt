package com.zhewen.kotlinsample.advanced.structuraldeclaration

/**
 * 结构声明简单演示
 */
fun main(){
    val (name,price) = Book("kotlin入门",66.6f)
    println(name)
    println(price)
    val (name1,price1) = Book1("Kotlin",99.0f)
    println(name1)
    println(price1)

    val (_,price2) = Book1("kotlin精通",100.2f)
    println(price2)

    //数组集合解构声明
    val array = arrayOf(1, 2, 3)
    val (a1, a2, a3) = array

    val list = listOf(1, 2, 3)
    val (b1, b2, b3) = list

    val map = mapOf("key1" to 1, "key2" to 2, "key3" to 3)
    for ((key, value) in map) {
        println("$key-$value")
    }

    Book1("kotlin 高阶",122.3f).showBookInfo { (name,price) ->
        println(name)
        println(price)
    }

}

data class Book(var name:String,var price:Float)

/**
 * 自定义结构声明
 */
class Book1(var name:String, var price: Float){

    operator fun component1():String{
        return name
    }

    operator fun component2():Float{
        return price
    }

    fun showBookInfo(block:(Book1) -> Unit){
        block.invoke(this)
    }
}

