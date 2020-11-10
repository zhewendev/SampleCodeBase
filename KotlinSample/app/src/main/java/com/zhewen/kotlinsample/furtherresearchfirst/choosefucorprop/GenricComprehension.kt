package com.zhewen.kotlinsample.furtherresearchfirst.choosefucorprop

import android.content.res.Resources
import android.util.TypedValue
import com.google.gson.Gson

/**
 * 泛型攻坚
 */
fun main(){
    val user = Gson().fromJson<User>(json)
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

inline fun <reified T>Resources.dpToPx(value: Int):T{
    val result = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value.toFloat(), displayMetrics)
    return when (T::class) {
        Float::class -> result as T
        Int::class -> result.toInt() as T
        else -> throw IllegalStateException("Type not supported")
    }
}