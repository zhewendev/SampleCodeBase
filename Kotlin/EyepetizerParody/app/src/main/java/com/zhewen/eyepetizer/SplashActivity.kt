package com.zhewen.eyepetizer

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.animation.ScaleAnimation
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.zhewen.eyepetizer.common.base.BaseActivity
import kotlinx.android.synthetic.main.eyepetizer_activity_splash.*
import pub.devrel.easypermissions.EasyPermissions

/**
 * @author: zhewen
 * created: 2020/8/6
 * desc:启动页
 */
class SplashActivity : BaseActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handlePermission()
    }

    override fun layoutId(): Int = R.layout.eyepetizer_activity_splash

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
        img_splash.startAnimation(scaleAnimation)
    }

    private fun handlePermission(permissions: Array<String>) {
        if (EasyPermissions.hasPermissions(this, *permissions))
    }



}