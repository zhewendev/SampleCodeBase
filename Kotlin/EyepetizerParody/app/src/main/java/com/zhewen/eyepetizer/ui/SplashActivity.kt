package com.zhewen.eyepetizer.ui

import android.Manifest
import android.graphics.Typeface
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SharedMemory
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.zhewen.eyepetizer.EyepetizerApplication
import com.zhewen.eyepetizer.R
import com.zhewen.eyepetizer.common.base.BaseSupportActivity
import com.zhewen.eyepetizer.common.utils.SharedPreferencesHelper
import com.zhewen.eyepetizer.common.widget.CommonDialog
import com.zhewen.eyepetizer.utils.Constants
import kotlinx.android.synthetic.main.eyepetizer_activity_splash.*
import pub.devrel.easypermissions.EasyPermissions

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
            loadRootFragment()  //todo
        } else{
            //加载常规加载页面
            loadRootFragment()
        }

    }

    private fun isFirstOpenApp(): Boolean {
        return SharedPreferencesHelper.get(Constants.KEY_IS_FIRST_OPEN_APP,false) as Boolean
    }


    private var mTextTypeface: Typeface? = null
    private var mDescTypeface: Typeface? = null


    init {
        mTextTypeface = Typeface.createFromAsset(
            EyepetizerApplication.getContext().assets,
            "fonts/Lobster-1.4.otf"
        )
        mDescTypeface = Typeface.createFromAsset(
            EyepetizerApplication.getContext().assets,
            "fonts/FZLanTingHeiS-L-GB-Regular.TTF"
        )
    }

    override fun getLayoutId(): Int {
        TODO("Not yet implemented")
    }

    override fun layoutId(): Int =
        R.layout.eyepetizer_activity_splash

    override fun initView() {
        tv_splash_center_tip.typeface = mTextTypeface
        tv_english_introduce.typeface = mDescTypeface
        ImmersionBar.with(this)
            .hideBar(BarHide.FLAG_HIDE_BAR)
            .init()
        startScaleAnimation()
    }

    private fun startScaleAnimation() {
        val scaleAnimation = ScaleAnimation(
            1.0f, 1.2f, 1.0f, 1.2f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5F,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5F
        )
        scaleAnimation.duration = 2000
        scaleAnimation.fillAfter = true
        scaleAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                handlePermission()
            }

            override fun onAnimationStart(p0: Animation?) {

            }
        })
        img_splash.startAnimation(scaleAnimation)
    }

    private fun handlePermission() {
        val perms = arrayOf(Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (!EasyPermissions.hasPermissions(this,*perms)) {
            //展示权限设置弹窗
            val commonDialog: CommonDialog = CommonDialog(this)
            commonDialog.show()
        }
    }


}