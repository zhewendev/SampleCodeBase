package com.zhewen.navigationcodelab.multiply

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.zhewen.navigationcodelab.R
import com.zhewen.navigationcodelab.fragment.BasicDemonstrationActivity
import com.zhewen.navigationcodelab.fragment.BasicFourFragment
import com.zhewen.navigationcodelab.fragment.BasicSecondFragment
import com.zhewen.navigationcodelab.fragment.BasicSevenFragment
import kotlin.random.Random

class MultiplyStackActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiply_stack)
        initView()
    }

    private fun initView() {
        findViewById<FragmentContainerView>(R.id.first_fragment_container_view).setOnClickListener {
            supportFragmentManager.setFragmentResult("requestMultiplyKey", bundleOf("bundleKey" to "result${java.util.Random(100)}"))
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack("first")
                replace<BasicSevenFragment>(R.id.first_fragment_container_view)
            }
        }
        findViewById<FragmentContainerView>(R.id.second_fragment_container_view).setOnClickListener {
            supportFragmentManager.setFragmentResult("requestMultiplyKey", bundleOf("bundleKey" to "result${java.util.Random(100)}"))
            supportFragmentManager.saveBackStack("first")
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack("second")
                replace<BasicSevenFragment>(R.id.second_fragment_container_view)
            }
        }
        findViewById<FragmentContainerView>(R.id.three_fragment_container_view).setOnClickListener {
            supportFragmentManager.setFragmentResult("requestMultiplyKey", bundleOf("bundleKey" to "result${java.util.Random(100)}"))
            supportFragmentManager.saveBackStack("second")
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack("three")
                replace<BasicSevenFragment>(R.id.three_fragment_container_view)
            }
        }
        findViewById<FragmentContainerView>(R.id.four_fragment_container_view).setOnClickListener {
            supportFragmentManager.setFragmentResult("requestMultiplyKey", bundleOf("bundleKey" to "result${java.util.Random(100)}"))
            supportFragmentManager.restoreBackStack("first")
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack("four")
                replace<BasicSevenFragment>(R.id.four_fragment_container_view)
            }
        }

    }
}