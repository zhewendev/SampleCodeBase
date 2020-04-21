package com.baiheng.simplekotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TabHost
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 声明一个延迟初始化的字符串数组变量
        val name : String by lazy {
            "nihao"
        }
        Toast.makeText(this,name,Toast.LENGTH_LONG).show()

    }
}
