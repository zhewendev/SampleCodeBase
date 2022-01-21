package com.zhewen.navigationcodelab.fragment

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.zhewen.navigationcodelab.R
import com.zhewen.navigationcodelab.handleBackPress

class BasicDemonstrationActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_demonstration)
        findViewById<Button>(R.id.switch_button).setOnClickListener {
            supportFragmentManager.commit {
                val bundle = bundleOf("origin" to "from activity switch button")
                setReorderingAllowed(true)
                addToBackStack(BasicSecondFragment.TAG)
                replace<BasicSecondFragment>(R.id.fragment_container_view,BasicSecondFragment.TAG,bundle)
            }
        }

    }

    override fun onBackPressed() {
        if (!handleBackPress(this)) {
            super.onBackPressed()
        }
    }
}