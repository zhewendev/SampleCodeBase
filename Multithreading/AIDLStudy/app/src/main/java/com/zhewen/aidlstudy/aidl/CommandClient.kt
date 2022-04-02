package com.zhewen.aidlstudy.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.IBinder
import android.os.Looper
import android.os.RemoteException
import android.util.Log
import android.widget.Toast
import com.zhewen.aidlserverstudy.IClientCallback
import com.zhewen.aidlserverstudy.ICommandServer
import com.zhewen.aidlserverstudy.User
import java.lang.ref.WeakReference

class CommandClient private constructor() {

    private var mRemoteService: ICommandServer? = null
    private var mContext:WeakReference<Context>? = null

    fun init(context:Context) {
        mContext = WeakReference(context)
    }

    private val mDeathRecipient:IBinder.DeathRecipient = object : IBinder.DeathRecipient {
        override fun binderDied() {
            Log.d(TAG,"binder died")
            mRemoteService?.asBinder()?.unlinkToDeath(this,0)
            mRemoteService = null
            connectService()
        }
    }

    private val mServiceConnection:ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName?, IBinder: IBinder?) {
            Log.d(TAG,"onServiceConnected")
            try {
                //设置死亡代理
                IBinder?.linkToDeath(mDeathRecipient,0)
                mRemoteService = ICommandServer.Stub.asInterface(IBinder)
                mRemoteService?.registerClientCallback(mClientCallback);
            } catch (e : RemoteException) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(componentName: ComponentName?) {
            Log.d(TAG,"onServiceDisconnected")
            mRemoteService?.unregisterClientCallback(mClientCallback)
            mRemoteService = null
        }
    }

    fun add(value1:Int, value2:Int):Int {
        return mRemoteService?.add(value1,value2)?:-1
    }

    fun addUserIn(){
        val user = User(11,"ClientAddUserIn")
        Log.d(TAG,"Client 调用 addUserIn 之前 User.name = ${user.name}")
        mRemoteService?.addUserIn(user)
        Log.d(TAG,"Client 调用 addUserIn 之后 User.name = ${user.name}")
    }

    fun addUserOut(){
        val user = User(22,"ClientAddUserOut")
        Log.d(TAG,"Client 调用 addUserOut 之前 User.name = ${user.name}")
        mRemoteService?.addUserOut(user)
        Log.d(TAG,"Client 调用 addUserOut 之后 User.name = ${user.name}")
    }

    fun addUserInOut(){
        val user = User(33,"ClientAddUserInOut")
        Log.d(TAG,"Client 调用 addUserInOut 之前 User.name = ${user.name}")
        mRemoteService?.addUserInOut(user)
//        Log.d(TAG,"Client 调用 addUserInOut 之后 User.name = ${user.name}")
        Log.d(TAG,"Client 调用 addUserInOut 之后 ${user.name}")
    }

    private val mClientCallback: IClientCallback.Stub = object : IClientCallback.Stub() {
        override fun doClientCallback(value: String?) {
            Log.d(TAG,"doClientCallback,value = $value")
        }
    }

    fun connectService() : Boolean? {
        val intent = Intent()
        intent.action = "com.zhewen.aidlserverstudy.aidl.commandservice"
        intent.`package` = "com.zhewen.aidlserverstudy"
        intent.component = ComponentName("com.zhewen.aidlserverstudy", "com.zhewen.aidlserverstudy.aidl.CommandService")
        return mContext?.get()?.bindService(intent,mServiceConnection, BIND_AUTO_CREATE)
    }


    companion object {
        val sInstance = SingletonHolder.holder
        const val TAG = "CommandClient"
    }

    private object SingletonHolder{
        val holder = CommandClient()
    }
}