package com.zhewen.kotlinsample.coroutines

import com.zhewen.kotlinsample.furtherresearch.second.log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.selects.select
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Exception
import java.lang.NullPointerException
import java.util.concurrent.Executors
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

//suspend fun main() {
//    log(1)
//    log(returnSuspended())
//    log(2)
//    delay(1000)
//    log(3)
//    log(returnImmediately())
//    log(4)
//}

suspend fun main(args: Array<String>) {

    runBlocking<Unit> {
        fun log(msg: Any) {
            println("${Thread.currentThread().name} msg=$msg")
        }

        log(1)

        launch {
            val a = 4
            delay(300)
            log(a)
        }
        launch {
            val b = 3
            delay(200)
            log(b)
        }
        launch {
            val c = 2
            delay(100)
            log(c)
        }
    }




//    val coroutineDispatcher = newSingleThreadContext("ctx")
////     启动协程 1
//    GlobalScope.launch(coroutineDispatcher) {
//        println("the first coroutine")
//        val deffery = async (Dispatchers.IO) {
//            println("the second coroutine 11111")
//            delay(100)
//            println("the second coroutine 222222")
//
//        }
//        println("the first coroutine end end end")
//        deffery.await()
//    }
//    // 保证 main 线程存活，确保上面两个协程运行完成
//    Thread.sleep(500)
//    testSelect()
//    testSelectChannel()
//    testFlow()
//    test()
//    log(1)
//    val job = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED,context = CoroutineName("Hahaha")) {
//        log(2)
//        delay(1000)
//        log(3)
//        println(coroutineContext[CoroutineName])
//    }
////    job.cancel()
//    log(4)
//    val jj:Int = 12
//    job.join()
//    Thread.sleep(2000)
//    println(Dispatchers.Main[CoroutineName])

//    val interceptor = object : ContinuationInterceptor {
//
//        override val key: CoroutineContext.Key<*> = ContinuationInterceptor
//
//        override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
//            println("intercept todo something. change run to thread")
//            return object : Continuation<T> by continuation {
//                override fun resumeWith(result: Result<T>) {
//                    println("create new thread")
//                    thread {
//                        continuation.resumeWith(result)
//                    }
//                }
//            }
//        }
//    }
//
//    val interceptor2 = object : ContinuationInterceptor {
//
//        override val key: CoroutineContext.Key<*> = ContinuationInterceptor
//
//        override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
//            println("in the second intercept todo something. change run to thread")
//            return object : Continuation<T> by continuation {
//                override fun resumeWith(result: Result<T>) {
//                    println("create new thread in second interceptor")
//                    thread {
//                        continuation.resumeWith(result)
//                    }
//                }
//            }
//        }
//    }
//
//    GlobalScope.launch(interceptor) {
//        println("launch start. current thread: ${Thread.currentThread().name}")
//
//        withContext(Dispatchers.IO + CoroutineName("Main")) {
//            println("new continuation todo something in the main thread. current thread: ${Thread.currentThread().name}")
//            delay(100)
//            println("new continuation todo something in the main thread. current thread: ${Thread.currentThread().name}")
//        }
//        println("launch end. current thread: ${Thread.currentThread().name}")
//
//        launch(interceptor2) {
//            println("new continuation todo something. current thread: ${Thread.currentThread().name}")
//        }
//
//        println("launch end. current thread: ${Thread.currentThread().name}")
//    }
//    Thread.sleep(3000)

}

//fun test3() = runBlocking{
//    val myDispatcher = Executors.newSingleThreadExecutor()
//        .asCoroutineDispatcher()
//    flow {
//        println("emit on ${Thread.currentThread().name}")
//        emit("data")
//    }.onStart {
//
//    }.onEach {
//
//    }.onCompletion {
//
//    }
//        .map {
//        println("run first map on${Thread.currentThread().name}")
//        "$it map"
//    }
//        //作用于前面flow创建与第一个map
//        .flowOn(Dispatchers.IO)
//        .map {
//            println("run second map on ${Thread.currentThread().name}")
//            "${it},${it.length}"
//        }
//        //作用于第二个map
//        .flowOn(myDispatcher)
//        .collect {
//            println("result $it on ${Thread.currentThread().name}")
//        }
//
//    val strA = "A"
//    val strB = "B"
//    if (strA sameAs strB) {//中缀调用 sameAs
//        println("str is the same")
//    } else {
//        println("str is the different")
//    }
//}
//
//private infix fun String.sameAs(strB: String): Boolean {
//    return this == strB
//}
//
//suspend fun test1() {
//    CoroutineScope(Dispatchers.IO).launch {
//        delay(11)
//    }
//}
//fun testSelect() = runBlocking {
//    val d1 = async {
//        delay(60)
//        1
//    }
//    val d2 = async {
//        delay(50)
//        2
//    }
//    val d3 = async {
//        delay(70)
//        3
//    }
//
//    val data = select<Int> {
//        d3.onAwait{ data ->
//            println("d3 first result $data")
//            data
//        }
//        d1.onAwait{ data ->
//            println("d1 first result $data")
//            data
//        }
//        d2.onAwait{ data ->
//            println("d2 first result $data")
//            data
//        }
//    }
//    println("result : $data")
//}
//
//fun testSelectChannel() = runBlocking {
//    val slowChannel = Channel<Int>(capacity = 1,onBufferOverflow = BufferOverflow.SUSPEND)
//    val fastChannel = Channel<Int>(capacity = 3,onBufferOverflow = BufferOverflow.SUSPEND)
//
//    launch(Dispatchers.IO) {
//        for (i in 1..5) {
//            if (!isActive) break
//            select<Unit> {
//                slowChannel.onSend(i){channel ->
//                    println("slow channel selected $i" )
//                }
//                fastChannel.onSend(i){channel ->
//                    println("fast channel selceted $i")
//                }
//            }
//        }
//        delay(300)
//        slowChannel.close()
//        fastChannel.close()
//    }
//
//    launch {
//        while (isActive && !slowChannel.isClosedForSend && !fastChannel.isClosedForSend) {
//            val result = select<Int> {
//                slowChannel.onReceiveCatching {
//                    println("slow channel is receive ${it.getOrNull()}")
//                    delay(100)
//                    it.getOrNull()?:-1
//                }
//                fastChannel.onReceiveCatching {
//                    println("fast channel is receive ${it.getOrNull()}")
//                    it.getOrNull()?:-1
//                }
//            }
//        }
//    }
//    delay(500)
//}
//
//suspend fun testFlow() {
//    val scope = CoroutineScope(SupervisorJob())
//    val flow = flow {
//        (0..2).forEach {
//            print("emit $it")
//            emit(it)
//        }
//    }
//
////    scope.launch {
////        flow.collect {
////            println("last collect result")
////        }
////    }
//    flow.map {  }
//
//}





//suspend fun returnSuspended() = suspendCoroutineUninterceptedOrReturn<String>{
//        continuation ->
//    thread {
//        Thread.sleep(1000)
//        continuation.resume("Return suspended.")
//    }
//    COROUTINE_SUSPENDED
//}
//
//suspend fun returnImmediately() = suspendCoroutineUninterceptedOrReturn<String>{
//    log(5)
//    "Return immediately."
//}
