package com.baiheng.simplekotlindemo

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TabHost
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlin.reflect.KProperty

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 声明一个延迟初始化的字符串数组变量
        val name : String by lazy {
            "nihao"
        }
        topLevelFunction()
        Singleton.sayHi()
        Log.d("MainActivity", "this Activity${this.tv_main.text}")
        Log.d("MainActivity","mTestView${mTestView.tv_main.text}")
//        val list: List<Test> = listOf(SecondTest(),ThirdTest())
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

        Log.d("MainActivity","name = " + Test<String>().name)
        Log.d("MainActivity","userName = " + Test<String>().userName)
        val test = Test<String>()
        test.userName = "hshishi"
        Log.d("MainActivity",test.userName)
        Log.d("MainActivity",test.name)
        Log.d("MainActivity","sex = " + Test<String>().sex)
        ST().handleSuperClass()

        val(jname,age,height) = TT("xiaoming",22)
        Log.d("MainActivity","jname = $jname")
        Log.d("MainActivity,","age =$age")
        Log.d("MainActivity,","height =$height")


    }

    fun test(onClickListener: View.OnClickListener = View.OnClickListener {  }) {

    }
}

