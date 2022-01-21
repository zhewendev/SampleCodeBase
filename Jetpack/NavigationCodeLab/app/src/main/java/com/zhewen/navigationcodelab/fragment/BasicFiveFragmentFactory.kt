package com.zhewen.navigationcodelab.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class BasicFiveFragmentFactory():FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(loadFragmentClass(classLoader,className)) {
            BasicFiveFragment::class.java -> BasicFiveFragment("create by custom factory")
            else -> super.instantiate(classLoader, className)
        }
    }
}