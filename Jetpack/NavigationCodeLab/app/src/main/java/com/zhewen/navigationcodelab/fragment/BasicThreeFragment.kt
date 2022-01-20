package com.zhewen.navigationcodelab.fragment

import androidx.fragment.app.Fragment
import com.zhewen.navigationcodelab.FragmentBackHandler
import com.zhewen.navigationcodelab.R
import com.zhewen.navigationcodelab.handleBackPress

class BasicThreeFragment:Fragment(R.layout.fragment_basic_three),FragmentBackHandler {
    override fun onBackPressed(): Boolean {
        return handleBackPress(this)
    }

}