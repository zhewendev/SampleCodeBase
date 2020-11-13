package com.zhewen.kotlinsample.furtherresearch.first

import android.text.TextUtils as Utils

/**
 * typealias类型别名理解
 */
fun main(){
    writeAliased { "Hello" }
//    writeInherited { "Hello" }    //编译报错
    writeInherited(object :InheritedSupplier{
        override fun invoke(): String {
            TODO("Not yet implemented")
        }
    })

    BoxedString.classVersion
//    Container<String>.classVersion    //报错
    Container.classVersion
    val name:String? = null
    Utils.isEmpty(name)

}

typealias AliasedSupplier = () -> String
interface InheritedSupplier : () -> String
typealias BoxedString = Container<String>

fun writeAliased(supplier: AliasedSupplier) =
    println(supplier.invoke())

fun writeInherited(supplier: InheritedSupplier) =
    println(supplier.invoke())

class Container<T>(var item: T) {
    companion object {
        const val classVersion = 5
    }
}