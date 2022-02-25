package com.zhewen.jetpacklab.hilt

import javax.inject.Inject

class Driver @Inject constructor() {
    constructor(driverName:String):this() {
        println("哈哈哈")
    }
}