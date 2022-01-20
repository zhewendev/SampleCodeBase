package com.zhewen.navigationcodelab.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.zhewen.navigationcodelab.FragmentBackHandler
import com.zhewen.navigationcodelab.R
import com.zhewen.navigationcodelab.handleBackPress

class BasicSecondFragment: Fragment(R.layout.fragment_basic_second),FragmentBackHandler {

    var external:String? = null
    private lateinit var tv1 : TextView
    private lateinit var btn:Button
    companion object {
        const val TAG = "BasicSecondFragment"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        external = requireArguments().getString("origin")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv1 = view.findViewById(R.id.show_external_message)
        btn = view.findViewById(R.id.switch_button);
        initView()
    }

    private fun initView() {
        tv1.text = external
        tv1.setOnClickListener {
            tv1.text = "setOnClickListener"
        }
        btn.setOnClickListener{
            childFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack(BasicFourFragment.TAG)
                replace<BasicFourFragment>(R.id.fragment_container_view)
            }
        }
    }

    override fun onBackPressed(): Boolean {
        return handleBackPress(this)
    }
}