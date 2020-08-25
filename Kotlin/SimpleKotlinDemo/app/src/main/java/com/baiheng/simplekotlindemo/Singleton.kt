package com.baiheng.simplekotlindemo

object Singleton {
    fun sayHi(){}
}
class User {
    var name = "Czh"
    val defaultName = "default"
    var userName: String = "username"
        set(value) {
            name = value
            field = value
        }

    //用val只读标识只读
    val sex: String
        get() = "男"

}