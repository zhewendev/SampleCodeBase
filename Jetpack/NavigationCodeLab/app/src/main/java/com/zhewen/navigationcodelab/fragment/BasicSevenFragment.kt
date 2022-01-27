package com.zhewen.navigationcodelab.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.MutableLiveData
import com.zhewen.navigationcodelab.R

class BasicSevenFragment: Fragment(R.layout.fragment_basic_seven) {

    companion object {
        const val TAG = "BasicSevenFragment"
    }

    private var externalStr = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        setFragmentResultListener("requestMultiplyKey") { _, bundle ->
            externalStr.value = bundle.getString("bundleKey")
            Log.d(TAG,"result = ${externalStr.value}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG,"onViewCreated")
        externalStr.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.message).text = it
        }


    }
}