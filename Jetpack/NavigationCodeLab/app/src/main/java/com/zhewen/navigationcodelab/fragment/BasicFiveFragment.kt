package com.zhewen.navigationcodelab.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import com.zhewen.navigationcodelab.FragmentBackHandler
import com.zhewen.navigationcodelab.R
import com.zhewen.navigationcodelab.handleBackPress

class BasicFiveFragment(private val status:String):Fragment(R.layout.fragment_basic_five),FragmentBackHandler {
    companion object {
        const val TAG = "BasicFiveFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(this@BasicFiveFragment.context,"toast $status",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this@BasicFiveFragment.context,"onResume",Toast.LENGTH_LONG).show()
        parentFragmentManager.commit {
            setMaxLifecycle(this@BasicFiveFragment,Lifecycle.State.CREATED)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG,"onDestroyView")
    }

    override fun onBackPressed(): Boolean {
        return handleBackPress(this)
    }
}