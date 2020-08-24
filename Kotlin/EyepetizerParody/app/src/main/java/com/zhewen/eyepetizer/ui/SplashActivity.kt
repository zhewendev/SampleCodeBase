package com.zhewen.eyepetizer.ui

import android.os.Bundle
import com.zhewen.eyepetizer.R
import com.zhewen.eyepetizer.common.base.BaseSupportActivity
import com.zhewen.eyepetizer.common.utils.SharedPreferencesHelper
import com.zhewen.eyepetizer.utils.Constants

/**
 * @author: zhewen
 * created: 2020/8/6
 * desc:启动页
 */
class SplashActivity : BaseSupportActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //首次进入，加载视频界面
        if (isFirstOpenApp()) {
            loadRootFragment(R.id.frame_container,VideoSplashFragment())
        } else{
            //加载常规加载页面
            loadRootFragment(R.id.frame_container,)
        }

    }

    override fun getLayoutId(): Int = R.layout.activity_splash

    private fun isFirstOpenApp(): Boolean {
        return SharedPreferencesHelper.get(Constants.KEY_IS_FIRST_OPEN_APP,false) as Boolean
    }


}