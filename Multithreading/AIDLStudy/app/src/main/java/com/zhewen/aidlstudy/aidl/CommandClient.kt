package com.zhewen.aidlstudy.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import com.zhewen.aidlserverstudy.IClientCallback
import com.zhewen.aidlserverstudy.ICommandServer
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
                mRemoteService?.registerClientCallback(mClientCallback,"");
            } catch (e : RemoteException) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(componentName: ComponentName?) {
            Log.d(TAG,"onServiceDisconnected")
            mRemoteService = null
        }
    }

    // TODO: 2021/9/18  
    fun add(value1:Int, value2:Int):Int {
        return mRemoteService?.add(value1,value2)?:-1
    }
    private val mClientCallback: IClientCallback.Stub = object : IClientCallback.Stub() {
        override fun doClientCallback() {
            TODO("Not yet implemented")
        }

    }

    fun connectService() : Boolean {
        val intent = Intent()
        intent.action = "com.zhewen.aidlserverstudy.aidl.commandservice"
        intent.`package` = "com.zhewen.aidlserverstudy"
        return mContext?.get()?.bindService(intent,mServiceConnection, BIND_AUTO_CREATE) ?: false
    }


    companion object {
        val sInstance = SingletonHolder.holder
        const val TAG = "CommandClient"
    }

    private object SingletonHolder{
        val holder = CommandClient()
    }
}