package com.baiheng.simplekotlindemo

import android.widget.TextView

open class Test<in T> {

    val height: Int = 22

    object Factory{
        var name:String = "小华"
        const val height:Int = 88
    }

    fun test(view : T){}

}