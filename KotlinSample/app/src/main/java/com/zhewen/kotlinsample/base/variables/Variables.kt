package com.zhewen.kotlinsample.base.variables

/**
 * 变量使用示例
 */
/**
 *相关知识
 * 1. kotlin中默认访问修饰符是public
 * 2. 如果变量没有初始化，需要显示声明类型并指明类型是否可null
 * 3. var/val 变量名 ： 类型? = null/确定的值    可空变量声明格式
 */
fun main () {
    val variable1:String = "hello world"    //只读变量
    var variable2:String = "hello kotlin"   //读写变量

    var x = 5   //自动推导变量为Int类型（快捷键显示 ctrl + shift + p）

    //可空变量
    var var_a: Int? = 0
    val val_a: Int? = null

    //可空变量调用处理
    /**
     * 使用判空处理(这种处理有什么问题吗？)
     */
    var str:String? = null
    if (str == null) {
        println("变量str的值为空")
    } else{
        println("str.length= ${str.length}")
    }

    /**
     * 使用?.处理
     */

    println("${str?.length}")

    /**
     * 使用let操作符
     */
    str?.let { println("str.length = ${str.length}") }

    /**
     * 使用Elvis操作符
     */

    println("str.length = ${str?.length?:0}")
    println("str.length = ${str!!.length}")
    println(str as? Any)


}

/**
 * 延迟初始化属性
 * 1.不能声明于可空变量和基本数据
 * 2.只能用于类体中的属性
 */
class Variable{
    private lateinit var person: Person
//    private lateinit var int: Int   //报错

    fun initPerson() {
        person = Person()
    }

    fun usePerson() {
        if (this::person.isInitialized) {   //检测变量是否初始化过
            println(person.name)
        }
    }
}

class Person{
    val name = "Peter"
}

/**
 * 延迟属性
 */
class LazyInit{
    val name: String by lazy {
        "peter"
    }

    val mNames:Array<out Any> by lazy {
        arrayOf(
                "Hello",
                1,
                true
        )
    }
}