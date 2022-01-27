package com.zhewen.navigationcodelab.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.zhewen.navigationcodelab.R
import com.zhewen.navigationcodelab.handleBackPress
import java.lang.ref.WeakReference


class BasicDemonstrationActivity:AppCompatActivity(),Notification {

    companion object{
        const val TAG = "BasicDemonstrationActivity"
    }

    var mMyHandler = MyHandler(this)
    private val basicDemonstrationActivityViewModel:BasicDemonstrationActivityViewModel by viewModels()
    private val sharedViewModel:SharedViewModel by viewModels()

    class MyHandler(activity: BasicDemonstrationActivity?) : Handler(Looper.getMainLooper()) {
        private val mActivity: WeakReference<BasicDemonstrationActivity> = WeakReference<BasicDemonstrationActivity>(activity)
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> Log.d(TAG, "正在处理Fragment的Handler消息")
                else -> {
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_demonstration)
        registerBroadcastReceiver()
        basicDemonstrationActivityViewModel.sharedData.postValue("basic")
        sharedViewModel.sharedData.postValue("sharedDataBasic")
        supportFragmentManager
            .setFragmentResultListener("requestDemoKey", this) { _, bundle ->
                val result = bundle.getString("bundleKey")
                if (result != null) {
                    Log.d(TAG,result)
                }
            }

        findViewById<Button>(R.id.switch_button).setOnClickListener {
            supportFragmentManager.commit {
                val bundle = bundleOf("origin" to "from activity switch button")
//                setCustomAnimations(
//                    R.anim.slide_in,
//                    R.anim.fade_out,
//                    R.anim.fade_in,
//                    R.anim.slide_out
//                )
                setReorderingAllowed(true)
                addToBackStack(BasicSecondFragment.TAG)
                replace<BasicSecondFragment>(R.id.fragment_container_view,BasicSecondFragment.TAG,bundle)
            }
        }
        findViewById<Button>(R.id.communicate_button).setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack(BasicSixFragment.TAG)
                replace<BasicSixFragment>(R.id.fragment_container_view)
            }
        }

    }


    /**
     * 注册广播
     */
    private fun registerBroadcastReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction("action_name")
        registerReceiver(mBroadcastReceiver, intentFilter)
    }

    /**
     * 创建广播接收器
     */
    private val mBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val action = intent.action
            if (action == "action_name") {
                Toast.makeText(this@BasicDemonstrationActivity, "广播", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed() {
        if (!handleBackPress(this)) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mBroadcastReceiver)
    }

    override fun notify(message: String) {
        Log.d(TAG,"notify $message")
    }
}