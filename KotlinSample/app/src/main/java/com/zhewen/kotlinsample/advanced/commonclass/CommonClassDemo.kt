package com.zhewen.kotlinsample.advanced.commonclass

/**
 * 类的简单演示，包括类的声明，构造函数与实例化
 */

class User(var name: String) {  //主构造函数
    init {  //初始化代码块
        println("name = $name")
    }

    constructor(name:String, age:Int):this(name){
        val age = age
        println(age)
    }
}

class User1 private constructor(val name: String) {
    init {
        println("init name = $name")
    }

    companion object{
        fun getInstance(name:String):User1{
            return User1(name)
        }
    }
}

class User2(val name: String = "peter",val age: Int = 22){
    init {
        println("name = $name,age = $age")
    }
}




fun main() {
    User("peter")
    User("peter",12)
    User1.getInstance("smith")
    User2()
    User2("Messi",33)
}