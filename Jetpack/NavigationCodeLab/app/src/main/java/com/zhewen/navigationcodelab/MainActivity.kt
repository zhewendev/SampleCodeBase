package com.zhewen.navigationcodelab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.zhewen.navigationcodelab.fragment.BasicDemonstrationActivity
import com.zhewen.navigationcodelab.fragment.BasicSecondDemonstrationActivity
import com.zhewen.navigationcodelab.multiply.MultiplyStackActivity
import com.zhewen.navigationcodelab.navigation.NavigationDemonstrationActivity

class MainActivity : AppCompatActivity(){

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var mFirstBtn: Button
    private lateinit var mSecondBtn: Button
    private lateinit var mThreeBtn: Button
    private lateinit var mFourBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        mFirstBtn = findViewById(R.id.jump_button_one)
        mSecondBtn = findViewById(R.id.jump_button_two)
        mThreeBtn = findViewById(R.id.jump_button_three)
        mFourBtn = findViewById(R.id.jump_button_four)
        mFirstBtn.setOnClickListener {
            startActivity<BasicDemonstrationActivity>(this@MainActivity) {}
        }
        mSecondBtn.setOnClickListener {
            startActivity<BasicSecondDemonstrationActivity>(this@MainActivity){}
        }
        mThreeBtn.setOnClickListener {
            startActivity<MultiplyStackActivity>(this@MainActivity){}
        }
        mFourBtn.setOnClickListener {
            startActivity<NavigationDemonstrationActivity>(this@MainActivity){}
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }
}