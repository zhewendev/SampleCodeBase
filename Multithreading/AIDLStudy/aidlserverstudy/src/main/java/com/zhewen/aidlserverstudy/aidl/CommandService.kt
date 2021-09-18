package com.zhewen.aidlserverstudy.aidl

import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import android.os.Parcel
import android.util.Log
import com.zhewen.aidlserverstudy.IClientCallback
import com.zhewen.aidlserverstudy.ICommandServer
import com.zhewen.aidlserverstudy.User

/**
 * 实现AIDL中定义的接口方法
 */
class CommandService: Service() {

    override fun onBind(p0: Intent?): IBinder? {
        //权限验证
        Log.d(TAG,"onBind")
//        val check = checkCallingOrSelfPermission("com.zhewen.aidlService.permission.ACCESS_SERVICE")
//        if (check == PackageManager.PERMISSION_DENIED) {
//            Log.d(TAG,"onBind,null")
//            return null
//        }
        return mRemoteBinder
    }

    private val mRemoteBinder:ICommandServer.Stub = object: ICommandServer.Stub() {
        override fun registerClientCallback(client: IClientCallback, pkgName: String?) {
            CommandServer.sInstance.registerClientCallback(client,pkgName)
        }

        override fun unregisterClientCallback(client: IClientCallback, pkgName: String?) {
            CommandServer.sInstance.unRegisterClientCallback(client,pkgName)
        }

        override fun add(value1: Int, value2: Int): Int {
            return CommandServer.sInstance.add(value1,value2)
        }

        override fun addUser(user: User?): MutableList<User> {
            return CommandServer.sInstance.addUser(user)
        }

        override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
            val clientPkgName = getCallingClientPkgName(getCallingUid())
            Log.d(TAG,"calling pkgName is $clientPkgName")
            return super.onTransact(code, data, reply, flags)
        }
    }

    private fun getCallingClientPkgName(callingUid:Int) : String {
        var packageName = ""
        val packages = packageManager.getPackagesForUid(callingUid)
        if (packages != null && packages.isNotEmpty()) {
            packageName = packages[0]
        }
        return packageName
    }

    companion object {
        const val TAG = "CommandService"
    }
}