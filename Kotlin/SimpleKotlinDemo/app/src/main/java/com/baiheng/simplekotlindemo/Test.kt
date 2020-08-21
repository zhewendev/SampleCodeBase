package com.baiheng.simplekotlindemo

import android.widget.TextView

open class Test<in T> {

    val height: Int = 22

    open var name = "Czh"
    var userName: String
        get() = name
        set(value) {
            name = value
        }
    val sex: String
        get() = "男"
//
//    var name: String = "Holmes, Sherlock"
//    var street: String = "Baker"
//
//    var city: String = "London"
//    var state: String? = null

    open fun test() {

    }

}