package com.zhewen.navigationcodelab

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context,T::class.java)
    intent.block()
    context.startActivity(intent)
}

fun handleBackPress(fragmentActivity: FragmentActivity) :Boolean {
    return handleBackPress(fragmentActivity.supportFragmentManager)
}

fun handleBackPress(fragment:Fragment) : Boolean {
    return handleBackPress(fragment.childFragmentManager)
}

fun handleBackPress(fragmentManager: FragmentManager) : Boolean {
    val fragments = fragmentManager.fragments
    for (i in 0 until fragments.size) {
        val child = fragments[i]
        if (isFragmentBackHandled(child)) return true
    }
    if (fragmentManager.backStackEntryCount > 0) {
        fragmentManager.popBackStack()
        return true
    }
    return false
}

fun isFragmentBackHandled(fragment: Fragment?): Boolean {
    return fragment != null &&
            fragment.isVisible &&
            fragment is FragmentBackHandler &&
            (fragment as FragmentBackHandler).onBackPressed()
}
