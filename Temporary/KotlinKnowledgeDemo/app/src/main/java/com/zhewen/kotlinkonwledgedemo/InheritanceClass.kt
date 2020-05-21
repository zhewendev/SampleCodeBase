package com.zhewen.kotlinkonwledgedemo

import android.util.Log

/**
* @author: zhewen
* created: 2020/5/19
* desc:
*/
class InheritanceClass {

    constructor(num : Int) {
        Log.d("InheritanceClass","constructor")
    }

    constructor(num: Int,str : String)

    fun test() {
        Log.d("InheritanceClass","test")
    }
}