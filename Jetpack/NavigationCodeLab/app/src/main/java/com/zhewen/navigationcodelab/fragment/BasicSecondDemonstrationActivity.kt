package com.zhewen.navigationcodelab.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.zhewen.navigationcodelab.R
import com.zhewen.navigationcodelab.handleBackPress

class BasicSecondDemonstrationActivity:AppCompatActivity(R.layout.activity_basic_second_demonstration) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = supportFragmentManager.findFragmentById(R.id.bottom_fragment_container)
        supportFragmentManager.commit {
            setPrimaryNavigationFragment(fragment)
        }
    }

    override fun onBackPressed() {
        if (!handleBackPress(this)) {
            super.onBackPressed()
        }
    }
}