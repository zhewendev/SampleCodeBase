package com.zhewen.jetpacklab

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.work.WorkManager
import com.zhewen.jetpacklab.databinding.ActivityMainBinding
import com.zhewen.jetpacklab.hilt.HiltFirstActivity
import com.zhewen.jetpacklab.workmanager.WorkManagerHomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()

    }

    private fun initView() {
        with(mBinding) {
            btnFirst.setOnClickListener {
//                startActivity<HiltFirstActivity>(this@MainActivity){}
                SystemClock.sleep(500)
            }
            btnSecond.setOnClickListener {
                startActivity<WorkManagerHomeActivity>(this@MainActivity){}
            }
        }
        val time = System.currentTimeMillis()
        mBinding.btnSecond.postDelayed({Log.d("MainActivity","result time is: ${(System.currentTimeMillis() - time) / 1000}")},5000)
//        Thread {
//            Thread.sleep(5000)
//            Log.d("MainActivity","result time is: ${System.currentTimeMillis() - time}")
//            println("MainActivity,${System.currentTimeMillis() - time}")
//        }
    }
}
