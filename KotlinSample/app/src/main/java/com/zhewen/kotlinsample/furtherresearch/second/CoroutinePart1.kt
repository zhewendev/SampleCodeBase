package com.zhewen.kotlinsample.furtherresearch.second

import kotlinx.coroutines.*
import org.jetbrains.annotations.TestOnly
import java.util.concurrent.Executors
import kotlin.concurrent.thread

/**
 * 协程知识与使用演示部分示例
 */
suspend fun main() {

    /**
     * kotlin中线程启动
     */
    val str = thread{   //立即启动线程
        println("thread")
    }

    val str1 = thread(start = false){
        println("thread not start")
    }
    str1.start()

    /**
     * 协程启动模式示例
     */
    println("--------------启动模式DEFAULT，饿汉式启动---------------")
    log(1)
    val job = GlobalScope.launch{
        log(2)
    }
    log(3)
    Thread.sleep(5000)	//防止程序退出，所以做了个sleep操作


    println("--------------启动模式LAZY，懒汉式启动---------------")
    log(1)
    val job1 = GlobalScope.launch(start = CoroutineStart.LAZY){
        log(2)
    }
    log(3)
//    job1.join()
    job1.start()
    log(4)


    println("--------------启动模式ATOMIC，启动---------------")
    log(1)
    val job2 = GlobalScope.launch(start = CoroutineStart.ATOMIC){
        log(2)
    }
    job2.cancel()
    log(3)
    Thread.sleep(2000)


    log(1)
    val job3 = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
        log(2)
        delay(1000) //挂起了协程
        log(3)
    }
    job3.cancel()
    log(4)
    job3.join()
    Thread.sleep(2000)

    println("--------------启动模式UNDISPATCHED，启动---------------")
    log(1)
    val job4 = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
        log(2)
        delay(100)
        log(3)
    }
    log(4)
    job4.join()
    log(5)
    Thread.sleep(2000)

    println("-------------------runBlocking方式创建协程方式----------------")
    runBlocking {
        launch {
            delay(1000L)
            println("world!")
        }
        println("Hello,")
        delay(2000L)
    }
    MyTest().testMySuspendingFunction()

    println("--------------------CoroutineScope.launch-------------")
    coroutineScope { // 创建一个协程作用域
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
    }

    println("------------------withContext---------------------")
    coroutineScope {
        launch(){   //从UI线程开始
            val number = withContext(Dispatchers.IO){   //切换到IO线程，执行完成后切回到UI线程
                println("running in IO Thread")
                12
            }
            println("get number = $number")
        }
    }

    println("------------------async 创建协程----------------")
    coroutineScope {
        launch {
            val avatar:Deferred<Unit> = async { println("avatar") }
            val logo:Deferred<String> = async {
                println("logo")
                "logo" }
            avatar.await()
            println(logo.await())
        }
    }

    println("-----------------拼接上下文-----------------")
    runBlocking(Dispatchers.Main.plus(CoroutineName("Hello"))) {}


}

suspend fun test() {
    Executors.newScheduledThreadPool(10)
        .asCoroutineDispatcher().use { dispatcher ->
            GlobalScope.launch(dispatcher) {
                log(1)
                // 这里会默认继承父协程的调度器
                val job = launch {
                    log(2)
                    delay(1000)
                    log(3)
                    "Hello"
                }
                log(4)
                val result = job.join()
                log("5. $result")
            }.join()
            log(6)
        }
}

class MyTest{

    @TestOnly
    fun testMySuspendingFunction() = runBlocking {
        println("runBlocking")
    }
}

fun log(o: Any?) {
    println("[${Thread.currentThread().name}]：$o")
}