package com.zhewen.aidlstudy

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zhewen.aidlstudy.aidl.CommandClient

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CommandClient.sInstance.init(this)
        findViewById<TextView>(R.id.bind_service).setOnClickListener(this)
        findViewById<TextView>(R.id.add_view).setOnClickListener(this)
        findViewById<TextView>(R.id.add_user_view).setOnClickListener(this)
//        CommandClient.sInstance.connectService()
        bindService()
    }


    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            //绑定服务成功回调
        }

        override fun onServiceDisconnected(name: ComponentName) {
            //服务断开时回调
        }
    }

    private fun bindService() {
        val intent = Intent()
        //Android 5.0开始，启动服务必须使用显示的，不能用隐式的
//        intent.setComponent(new ComponentName("com.vivo.a11085273.othertest", "com.vivo.a11085273.othertest.MyAidlService"));
        intent.action = "com.zhewen.aidlserverstudy.aidl.commandservice"
        intent.setPackage("com.zhewen.aidlserverstudy")
//        intent.component = ComponentName("com.zhewen.aidlserverstudy", "com.zhewen.aidlserverstudy.aidl.CommandService")
//        startService(intent);
        val result = bindService(intent, connection, BIND_AUTO_CREATE)
        Log.d("MainActivity", "bindService$result")
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.bind_service -> {
                val result = CommandClient.sInstance.connectService()
                findViewById<TextView>(R.id.bind_service).text = "服务绑定操作$result"
            }
            R.id.add_view -> {
               val result =  CommandClient.sInstance.add(10,19)
                findViewById<TextView>(R.id.add_view_result_show).text = "结果——>$result"
            }
            R.id.add_user_view -> {

            }
            R.id.open_other_app -> {

            }
            else -> {

            }
        }
    }
}