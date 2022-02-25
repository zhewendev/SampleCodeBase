package com.zhewen.jetpacklab.hilt

import javax.inject.Inject

class GasEngine @Inject constructor() :Engine {
    override fun start() {
        println("GasEngine start")
    }

    override fun shutdown() {
        println("GasEngine shutdown")
    }
}