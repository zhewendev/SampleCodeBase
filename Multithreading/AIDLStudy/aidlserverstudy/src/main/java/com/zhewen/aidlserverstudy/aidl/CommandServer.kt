package com.zhewen.aidlserverstudy.aidl

import android.os.RemoteCallbackList
import com.zhewen.aidlserverstudy.IClientCallback
import com.zhewen.aidlserverstudy.User

class CommandServer private constructor(){

    companion object {
        val sInstance = SingletonHolder.holder
    }

    private object SingletonHolder{
        val holder = CommandServer()
    }

    private val mClientCallbackList = RemoteCallbackList<IClientCallback>()

    //todo
    fun registerClientCallback(client: IClientCallback, pkgName:String?) {
        mClientCallbackList.register(client)
    }

    fun unRegisterClientCallback(client: IClientCallback,pkgName: String?) {
        mClientCallbackList.unregister(client);
    }

    fun add(value1:Int,value2:Int):Int {
        return value1 + value2
    }

    fun addUser(user: User?): MutableList<User> {
        return mutableListOf(User(1,""))
    }

}