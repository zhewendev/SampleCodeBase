package com.baiheng.simplekotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TabHost
import android.widget.TextView
import android.widget.Toast
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 声明一个延迟初始化的字符串数组变量
        val name : String by lazy {
            "nihao"
        }
        println(Test("小明",15).height)
        println(Test.Factory.height)
        topLevelFunction()
        val list: List<out Test> = listOf(SecondTest(),ThirdTest())
//        Toast.makeText(this,name,Toast.LENGTH_LONG).show()
//        val str : String = "Hello world!"
//        println("strLength = ${str.length}")
//        println("strIndices = ${str.indices}")
//        println("strLastIndex = ${str.lastIndex}")
//        println("strFirst = ${str.first()}")
//        println("strFind = ${str.find { it == 'W' }}")
//        println("strLast = ${str.last()}")
//        println("strIndexOfFirst = ${str.indexOfFirst { it == 'H'}}")
//        println("strIndexOf = ${str.indexOf("Hello",0)}")
        val str = "Kotlin is a very good programming language"
//        println("subStr = ${str.substring(10)}")  // 当只有开始下标时，结束下标为length - 1
//        println(str.substring(0,15))
//        println(str.substring(IntRange(0,15)))
//        println("subSeq = ${str.subSequence(IntRange(0,15))}")
        println(str.replace('a','A'))









    }
}
