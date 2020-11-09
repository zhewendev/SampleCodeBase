package com.zhewen.kotlinsample.higherorder.extension

/**
 * 扩展简单示例
 */
fun main() {

    val mutableList = mutableListOf(1,2,3)
    mutableList.swap(1,2)
    println(mutableList)

    User().print()

    println(null.toString())
    User1.foo()


    Person().getUser(User2())
}

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // “this”对应该列表
    this[index1] = this[index2]
    this[index2] = tmp
}

/**
 * 内部成员函数与扩展函数冲突
 */
class User{
    fun print(){
        println("内部成员函数")
    }
}

fun User.print(){
    println("扩展函数")
}

fun Any?.toString():String{
    if (this == null) return "null"
    return toString()
}

/**
 * 扩展伴生对象
 */
class User1{
    companion object{

    }
}

fun User1.Companion.foo(){
    println("扩展伴生对象")
}

/**
 * 在一个类内部为另一个类声明扩展
 */
class User2{
    fun printUser(){
        println("User2")
    }
}

class Person{
    fun printPerson(){
        println("Person")
    }

    fun printUser(){
        println("Person.User")
    }

    //扩展函数
    fun User2.print(){
        printUser()
        printPerson()
        this@Person.printUser()
    }

    fun getUser(user:User2){
        user.print()
    }
}