package com.zhewen.mvppackage.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.zhewen.mvppackage.MyApplication
import com.zhewen.mvppackage.R
import com.zhewen.mvppackage.base.BaseActivity
import com.zhewen.mvppackage.utils.AppUtils
import kotlinx.android.synthetic.main.activity_splash.*
import pub.devrel.easypermissions.EasyPermissions

class SplashActivity : BaseActivity() {

    private var textTypeface: Typeface? = null

    private var descTypeFace: Typeface? = null

    private var alphaAnimation: AlphaAnimation? = null

    init {
        textTypeface =
            Typeface.createFromAsset(MyApplication.context.assets, "fonts/Lobster-1.4.otf")
        descTypeFace = Typeface.createFromAsset(
            MyApplication.context.assets,
            "fonts/FZLanTingHeiS-L-GB-Regular.TTF"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
    }

    override fun layoutId(): Int = R.layout.activity_splash

    @SuppressLint("SetTextI18n")
    override fun initView() {
        tv_app_name.typeface = textTypeface
        tv_splash_desc.typeface = descTypeFace
        tv_version_name.text = "v${AppUtils.getVersionName(MyApplication.context)}"

        //渐变展示启动页
        alphaAnimation = AlphaAnimation(0.2f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                redirectTo()
            }
        })

    }

    override fun initData() {
    }

    override fun start() {
    }

    override fun onStart() {
        super.onStart()
        checkPermission()
    }

    private fun checkPermission() {
        val perms = arrayOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        EasyPermissions.requestPermissions(this, "KotlinMvp应用需要以下权限，请允许", 0, *perms)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (requestCode == 0) {
            if (perms.isNotEmpty()) {
                if (perms.contains(Manifest.permission.READ_PHONE_STATE)
                    && perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ) {
                    if (alphaAnimation != null) {
                        iv_web_icon.startAnimation(alphaAnimation)
                    }
                }
            }
        }
    }

    fun redirectTo() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
