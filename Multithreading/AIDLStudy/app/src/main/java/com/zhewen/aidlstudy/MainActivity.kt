package com.zhewen.aidlstudy

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
        findViewById<TextView>(R.id.tv_first).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_second).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_third).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_four).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_five).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_six).setOnClickListener(this)
        CommandClient.sInstance.connectService()
        bindService()
    }


    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.d("MainActivity","onServiceConnected")
            //绑定服务成功回调
        }

        override fun onServiceDisconnected(name: ComponentName) {
            //服务断开时回调
        }
    }

    private fun bindService() {
        val intent = Intent()
        //Android 5.0开始，启动服务必须使用显示的，不能用隐式的
        intent.action = "com.zhewen.aidlserverstudy.aidl.commandservice"
        intent.`package` = "com.zhewen.aidlserverstudy"
        intent.component = ComponentName("com.zhewen.aidlserverstudy", "com.zhewen.aidlserverstudy.aidl.CommandService")
        val result = bindService(intent, connection, BIND_AUTO_CREATE)
        Log.d("MainActivity", "bindService$result")
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.tv_first -> {
                val result = CommandClient.sInstance.connectService()
                findViewById<TextView>(R.id.tv_first).text = "服务绑定操作结果为:$result"
            }
            R.id.tv_second -> {
               val result =  CommandClient.sInstance.add(10,19)
                findViewById<TextView>(R.id.tv_second).text = "服务端add方法结果——>$result"
            }
            R.id.tv_third -> {
                CommandClient.sInstance.addUserIn()
            }
            R.id.tv_four -> {
                CommandClient.sInstance.addUserOut()
            }
            R.id.tv_five -> {
                CommandClient.sInstance.addUserInOut()
            }
            R.id.tv_six -> {

            }
            else -> {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }
}