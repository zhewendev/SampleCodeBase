package com.zhewen.jetpacklab.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhewen.jetpacklab.databinding.ActivityHiltFirstBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltFirstActivity:AppCompatActivity() {
    private lateinit var mBinding:ActivityHiltFirstBinding

    @Inject
    lateinit var mTruck: Truck

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHiltFirstBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mTruck.deliver()
    }
}