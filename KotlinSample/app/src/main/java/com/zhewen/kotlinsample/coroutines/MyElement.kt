package com.zhewen.kotlinsample.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun test() {
    CoroutineScope(Dispatchers.IO).launch {
        delay(11)
    }
}