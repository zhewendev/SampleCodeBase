package com.zhewen.aidlserverstudy.aidl

import android.os.RemoteCallbackList
import android.os.RemoteException
import com.zhewen.aidlserverstudy.IClientCallback
import com.zhewen.aidlserverstudy.User

class CommandServer private constructor(){

    companion object {
        val sInstance = SingletonHolder.holder
    }

    private object SingletonHolder{
        val holder = CommandServer()
    }

    //如果客户端异常死亡了，这个对应的回调会自己消失,可在这里进行死亡监听
    private val mClientCallbackList = object :RemoteCallbackList<IClientCallback>(){

        override fun onCallbackDied(callback: IClientCallback?, cookie: Any?) {
            super.onCallbackDied(callback, cookie)
        }
    }

    fun registerClientCallback(client: IClientCallback) {
        mClientCallbackList.register(client)
    }

    fun unRegisterClientCallback(client: IClientCallback) {
        mClientCallbackList.unregister(client);
    }

    fun add(value1:Int,value2:Int):Int {
        return value1 + value2
    }

    fun addUser(user: User?): MutableList<User> {
        return mutableListOf(User(1,""))
    }

    fun onDataChange(user: User) {
        val callbackCount = mClientCallbackList.beginBroadcast()
        for (i in 0 until callbackCount) {
            try {
                mClientCallbackList.getBroadcastItem(i)?.doClientCallback(user.age.toString())
            } catch (e:RemoteException) {
                e.printStackTrace()
            }
        }
        mClientCallbackList.finishBroadcast()
    }

    fun clean() {
        mClientCallbackList.kill()
    }
}