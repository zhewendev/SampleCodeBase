package com.zhewen.navigationcodelab.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Lifecycle
import androidx.transition.TransitionInflater
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity = context as Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        external = arguments?.getString("origin")?:""
        childFragmentManager.fragmentFactory = BasicFiveFragmentFactory()
        parentFragmentManager.setFragmentResult("requestDemoKey", bundleOf("bundleKey" to "from basic second Fragment"))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv1 = view.findViewById(R.id.show_external_message)
        btn = view.findViewById(R.id.switch_button)
        initView()
    }

    private fun initView() {
        tv1.text = external
        tv1.setOnClickListener {
            tv1.text = "setOnClickListener"
        }
        var flag = true
        btn.setOnClickListener{
            if (flag) {
                flag = false
                childFragmentManager.commit {
                    setReorderingAllowed(true)
                    addToBackStack(BasicFourFragment.TAG)
                    replace<BasicFourFragment>(R.id.fragment_container_view)
                }
            } else {
                flag = true
                val result = "result"
                childFragmentManager.setFragmentResult("requestKey", bundleOf("bundleKey" to result))
                childFragmentManager.commit {
                    setReorderingAllowed(true)
                    addToBackStack(BasicFiveFragment.TAG)
                    replace<BasicFiveFragment>(R.id.fragment_container_view)
//                    val basicFiveFragment = BasicFiveFragment("error")
//                    replace(R.id.fragment_container_view,basicFiveFragment)
//                    setMaxLifecycle(basicFiveFragment,Lifecycle.State.CREATED)
                }
            }
        }
    }

    override fun onBackPressed(): Boolean {
        return handleBackPress(this)
    }
}