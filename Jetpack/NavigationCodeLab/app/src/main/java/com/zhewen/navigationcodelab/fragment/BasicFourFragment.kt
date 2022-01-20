package com.zhewen.navigationcodelab.fragment

import androidx.fragment.app.Fragment
import com.zhewen.navigationcodelab.FragmentBackHandler
import com.zhewen.navigationcodelab.R
import com.zhewen.navigationcodelab.handleBackPress

class BasicFourFragment:Fragment(R.layout.fragment_basic_four),FragmentBackHandler {

    companion object{
        const val TAG = "BasicFourFragment"
    }

    override fun onBackPressed(): Boolean {
        return handleBackPress(this)
    }
}