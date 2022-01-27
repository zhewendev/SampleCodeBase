package com.zhewen.navigationcodelab.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import com.zhewen.navigationcodelab.FragmentBackHandler
import com.zhewen.navigationcodelab.R
import com.zhewen.navigationcodelab.handleBackPress

class BasicFiveFragment(private val status:String):Fragment(R.layout.fragment_basic_five),FragmentBackHandler {
    companion object {
        const val TAG = "BasicFiveFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG,"onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        setFragmentResultListener("requestKey") { _, bundle ->
            val result = bundle.getString("bundleKey")
            Log.d(TAG,"getResult = $result")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"onViewCreated")
        Toast.makeText(this@BasicFiveFragment.context,"toast $status",Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
        Toast.makeText(this@BasicFiveFragment.context,"onResume",Toast.LENGTH_LONG).show()
//        parentFragmentManager.commit {
//            setMaxLifecycle(this@BasicFiveFragment,Lifecycle.State.CREATED)
//        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG,"onDestroyView")
    }

    override fun onBackPressed(): Boolean {
        return handleBackPress(this)
    }
}