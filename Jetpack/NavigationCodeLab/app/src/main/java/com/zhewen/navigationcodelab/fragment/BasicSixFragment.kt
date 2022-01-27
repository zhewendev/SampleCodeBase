package com.zhewen.navigationcodelab.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.zhewen.navigationcodelab.FragmentBackHandler
import com.zhewen.navigationcodelab.R
import com.zhewen.navigationcodelab.handleBackPress


class BasicSixFragment: Fragment(R.layout.fragment_basic_six),FragmentBackHandler {

    companion object{
        const val TAG = "BasicSixFragment"
    }
    private lateinit var mHandler:BasicDemonstrationActivity.MyHandler
    private lateinit var mActivity:Activity
    private lateinit var mActivityListener:Notification


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
        if (context is BasicDemonstrationActivity) {
            mHandler = context.mMyHandler
            mActivityListener = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val message: Message = Message.obtain()
        message.what = 1
        mHandler.sendMessage(message)
        val intent = Intent("action_name")
        mActivity.sendBroadcast(intent)
        if (::mActivityListener.isInitialized) {
            mActivityListener.notify("from basic six fragment")
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.show_dialog).setOnClickListener {
            BasicDialogFragment().show(childFragmentManager,BasicDialogFragment.TAG)
        }
    }

    override fun onBackPressed(): Boolean {
        return handleBackPress(this)
    }

}