package com.zhewen.jetpacklab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhewen.jetpacklab.databinding.ActivityMainBinding
import com.zhewen.jetpacklab.hilt.HiltFirstActivity
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
                startActivity<HiltFirstActivity>(this@MainActivity){}
            }
        }
    }
}