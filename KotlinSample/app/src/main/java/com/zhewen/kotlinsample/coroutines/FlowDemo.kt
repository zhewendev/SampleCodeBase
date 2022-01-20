package com.zhewen.kotlinsample.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.concurrent.thread

fun main() {
    runBlocking {
        val stateFlow = MutableStateFlow(1)
        val readOnlyStateFlow = stateFlow.asStateFlow()
        //模拟外部立即订阅数据
        val launch1 = launch(Dispatchers.IO) {
            readOnlyStateFlow.collect {
                println("collect0 $it")
            }
        }
        delay(50)

        //模拟在另一个类发送数据
        val launch4 = launch(Dispatchers.IO) {
            for (i in 1..3) {
                println("wait emit $i")
                stateFlow.emit(i)
                delay(50)
            }
        }

        //模拟启动页面，在新页面订阅
        val launch2 = launch(Dispatchers.IO) {
            readOnlyStateFlow.collect{
                println("collect1 $it")
            }
        }

        val launch3 = launch(Dispatchers.IO) {
            readOnlyStateFlow.collect{
                println("collect2 $it")
            }
        }
        println("get value : ${readOnlyStateFlow.value}")
        delay(200)
        launch1.cancel()
        launch2.cancel()
        launch3.cancel()
        launch4.cancel()
    }
}

fun testFlow() = runBlocking {
    val sharedFlow = MutableSharedFlow<String>(replay = 1)
    launch(Dispatchers.IO) {
        for (i in 0..10) {
            sharedFlow.emit("data$i")
            delay(50)
        }
    }
    //模拟外部调用
    delay(110)
    val readOnlySharedFlow = sharedFlow.asSharedFlow()
    launch(Dispatchers.IO) {
        readOnlySharedFlow.map {
            "$it receiver 1"
        }.collect {
            println(it)
        }
    }
    delay(50)
    launch(Dispatchers.IO) {
        readOnlySharedFlow.map {
            "$it receiver 2"
        }.collect {
            println(it)
        }
        readOnlySharedFlow
    }

}

//    sharedFlow.collect {
//        println(it)
//    }


