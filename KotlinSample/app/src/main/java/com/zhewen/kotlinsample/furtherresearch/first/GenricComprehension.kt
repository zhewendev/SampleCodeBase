package com.zhewen.kotlinsample.furtherresearch.first

import android.content.res.Resources
import android.util.TypedValue
import com.google.gson.Gson

/**
 * 泛型攻坚
 */
fun main() {
    val user = Gson().fromJson<User>(json)

    val stringList: List<String> = listOf("a", "b", "c", "d")
    val intList: List<Int> = listOf(1, 2, 3, 4)
    printList(stringList)//向函数传递一个List<String>函数实参,也就是这里List<String>是可以替换List<Any>
    printList(intList)//向函数传递一个List<Int>函数实参,也就是这里List<Int>是可以替换List<Any>
//    printMutableList(stringList)  //报错

//    val consumer = Consumer<Small>()
//    val consumer1 = Consumer<Medium>()
//    val consumer2:Consumer<Small> = consumer1

    isOddList(intList)


}

data class User(val first: String, val last: String)

val json = """{
      "first": "Sherlock",
      "last": "Holmes"
    }"""

inline fun <reified T> Gson.fromJson(json: String) =
    fromJson(json, T::class.java)

fun <T> Int.toType(): T? {
    return (this as? T)
}

//fun Resources.dpToPx(value: Int): Float {
//    return TypedValue.applyDimension(
//        TypedValue.COMPLEX_UNIT_DIP,
//        value.toFloat(), displayMetrics)
//}
//
//fun Resources.dpToPx(value: Int): Int {
//    val floatValue: Float = dpToPx(value)
//    return floatValue.toInt()
//}
/**
 * reified实例化参数示例
 */
inline fun <reified T> Resources.dpToPx(value: Int): T {
    val result = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value.toFloat(), displayMetrics
    )
    return when (T::class) {
        Float::class -> result as T
        Int::class -> result.toInt() as T
        else -> throw IllegalStateException("Type not supported")
    }
}

fun printList(list: List<Any>) {
//注意：这里函数形参类型是List<Any>,函数内部是不知道外部传入是List<Int>还是List<String>,全部当做List<Any>处理
    list.forEach {
        println(it)
    }
}

fun printMutableList(mutableList: MutableList<Any>) {
    mutableList.forEach {
        println(it)
    }
}

class Consumer<out T : Small> {

    var name: @UnsafeVariance T = TODO()
        get() = TODO()

    fun indexOf(element: @UnsafeVariance T) {

    }
}

class Size<out T : Small> {

}


open class Small {
    var weight: Int = 12
}

class Medium : Small() {

}

//java版写法
/*******************************
public void isOddList(){
int count = 0;
for(int i = 0; i < numberList.size(); i++){
if(numberList[i] % 2 == 1){
count++;
}
}
if(count == numberList.size()){
System.out.println("奇数集合");
return;
}
System.out.println("不是奇数集合");
}
 ***************************/

fun isOddList(numberList: List<Int>) = (numberList.filter {
    it % 2 == 1
}.count() == numberList.size).yes { "奇数集合" }.otherwise { "不是奇数集合" }

inline fun <T>Boolean.yes(block: () -> T): Boolean {
    if (this) println(block.invoke())
    return this
}

inline fun <T>Boolean.otherwise(block: () -> T): Boolean {
    if (!this) println(block.invoke())
    return this
}
