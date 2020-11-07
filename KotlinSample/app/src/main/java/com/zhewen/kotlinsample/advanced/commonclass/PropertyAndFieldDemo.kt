package com.zhewen.kotlinsample.advanced.commonclass

/**
 * 属性与字段使用简单示例
 */
class Property(val name: String) {
    var value: String = ""
        get() = field
        set(value) {
            field = if (name == value) value else name.plus(value)
        }

    var tag :String = ""        //设置可见性
        private set
}

fun main() {
    val property = Property("test")
    println(property.name)
    property.value = "peter"
    println(property.value)
}