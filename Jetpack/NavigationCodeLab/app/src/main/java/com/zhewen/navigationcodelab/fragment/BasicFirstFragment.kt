package com.zhewen.navigationcodelab.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import com.zhewen.navigationcodelab.FragmentBackHandler
import com.zhewen.navigationcodelab.R
import com.zhewen.navigationcodelab.handleBackPress

class BasicFirstFragment:Fragment(),FragmentBackHandler {

    private val basicDemonstrationActivityViewModel:BasicDemonstrationActivityViewModel by activityViewModels()
    private val sharedViewModel:SharedViewModel by activityViewModels()

    companion object{
        const val TAG = "BasicFirstFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_basic_first,container,false)
        Log.d(TAG,"onCreateView")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basicDemonstrationActivityViewModel.sharedData.observe(this, {
            Log.d(TAG, "$it basic")
        })
        sharedViewModel.sharedData.observe(this, {
            Log.d(TAG, "$it basic")
        })
        val tv1 = view.findViewById<TextView>(R.id.message)
        tv1.setOnClickListener {
            tv1.text = "change status by click"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG,"onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }

    override fun onBackPressed(): Boolean {
        return handleBackPress(this)
    }
}