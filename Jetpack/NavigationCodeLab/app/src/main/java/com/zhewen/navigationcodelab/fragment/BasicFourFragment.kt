package com.zhewen.navigationcodelab.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.zhewen.navigationcodelab.FragmentBackHandler
import com.zhewen.navigationcodelab.R
import com.zhewen.navigationcodelab.handleBackPress

class BasicFourFragment:Fragment(R.layout.fragment_basic_four),FragmentBackHandler {

    companion object{
        const val TAG = "BasicFourFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        setFragmentResultListener("requestKKey") { _, bundle ->
            val result = bundle.getString("bundleKey")
            Log.d(TAG,"getResult = $result")
        }
    }

    override fun onBackPressed(): Boolean {
        return handleBackPress(this)
    }
}