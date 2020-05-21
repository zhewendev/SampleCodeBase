package com.zhewen.mvppackage.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pub.devrel.easypermissions.EasyPermissions

abstract class BaseActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initData()
        initView()
        start()
//        initListener()


    }

    /**
     * load layout
     */
    abstract fun layoutId() : Int

    /**
     * init data
     */
    abstract fun initData()

    /**
     * init view
     */
    abstract fun initView()

    /**
     * start request
     */
    abstract fun start()

}