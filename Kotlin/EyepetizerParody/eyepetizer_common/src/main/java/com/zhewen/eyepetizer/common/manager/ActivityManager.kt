package com.zhewen.eyepetizer.common.manager

import android.app.Activity

/**
* @author: zhewen
* created: 2020/8/20
* desc:Activity管理类
*/
class ActivityManager private constructor(){
    private val sActivityList = ArrayList<Activity>()

    companion object {
        fun getInstance(): ActivityManager {
            return BaseAppManagerHolder.instance
        }
    }

    private object BaseAppManagerHolder {
        val instance = ActivityManager()
    }

    /**
     * 添加activity入栈
     */
    fun addActivity(activity: Activity) {
        sActivityList.add(activity)
    }

    /**
     * activity从栈中移除
     */
    fun removeActivity(activity: Activity) {
        if (activity in sActivityList) sActivityList.remove(activity)
    }

    /**
     * 清除栈内数据
     */
    fun clearAllActivity() {
        for (activity in sActivityList) {
            sActivityList.remove(activity)
        }
    }
}