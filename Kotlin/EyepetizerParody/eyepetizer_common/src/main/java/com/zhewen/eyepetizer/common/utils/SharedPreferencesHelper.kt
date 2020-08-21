package com.zhewen.eyepetizer.common.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

/**
* @author: zhewen
* created: 2020/8/21
* desc:用户配置信息
*/
object SharedPreferencesHelper {

    private const val SHARED_PREFS_FILE_NAME: String = "prefs_eyepetizer"

    private val mPrefs: SharedPreferences
    private val mEditor: SharedPreferences.Editor
    init {
        mPrefs = Application().getSharedPreferences(SHARED_PREFS_FILE_NAME,Context.MODE_PRIVATE)
        mEditor = mPrefs.edit()
    }


    /**
     * 保存数据
     */
    fun put(key: String, any: Any): Boolean {
        when(any) {
            is String -> mEditor.putString(key, any)
            is Boolean -> mEditor.putBoolean(key, any)
            is Int -> mEditor.putInt(key, any)
            is Float -> mEditor.putFloat(key, any)
            is Long -> mEditor.putLong(key, any)
            is Set<*> -> mEditor.putStringSet(key, any as? (Set<String>))
            else -> {
                mEditor.putString(key, any.toString())
            }
        }
        return mEditor.commit()
    }

    /**
     * 获取数据
     */
    fun get(key: String, defaultObj: Any): Any {
        return when(defaultObj) {
            is String -> (mPrefs.getString(key,defaultObj))?: ""
            is Boolean -> mPrefs.getBoolean(key,defaultObj)
            is Int -> mPrefs.getInt(key, defaultObj)
            is Float -> mPrefs.getFloat(key,defaultObj)
            is Long -> mPrefs.getLong(key,defaultObj)
            else -> {
                mPrefs.getString(key,defaultObj.toString())?: ""
            }
        }
    }


}