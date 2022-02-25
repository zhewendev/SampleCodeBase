package com.zhewen.jetpacklab.hilt

import javax.inject.Inject

class Truck @Inject constructor(val driver: Driver){

    @Inject
    lateinit var mEngine: Engine

    fun deliver() {
        mEngine.start()
        println("Truck is delivering cargo. driver is $driver")
        mEngine.shutdown()
    }
}