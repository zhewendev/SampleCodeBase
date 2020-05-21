package com.zhewen.kotlinmvp.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

/**
* @author: zhewen
* created: 2020/5/19
* desc: Tools category
*/
class AppUtils private constructor(){
    init {
        throw Error("Do not need instantiate!")
    }
    companion object {
        private val DEBUG = true
        private val TAG = AppUtils::class.java.simpleName

        /**
         * get version name
         */
        fun getVersionName(context: Context): String {
            var verName = ""
            try {
                val packageName = context.packageName
                verName = context.packageManager
                    .getPackageInfo(packageName,0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return verName
        }

        /**
         * get version code
         */
        fun getVerCode(context: Context): Int {
            var verCode = -1
            try {
                val packageName = context.packageName
                val packageInfo = context.packageManager
                    .getPackageInfo(packageName, 0)
                verCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    packageInfo.longVersionCode.toInt()
                } else {
                    packageInfo.versionCode
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return verCode
        }



    }
}