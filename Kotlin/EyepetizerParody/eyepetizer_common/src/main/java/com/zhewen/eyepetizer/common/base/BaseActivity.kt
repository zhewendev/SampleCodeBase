package com.zhewen.eyepetizer.common.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

abstract class BaseActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    companion object {
        const val TAG: String = "BaseActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        getExtra()
        initView()
    }

    /**
     * 获取布局视图
     */
    abstract fun layoutId() : Int

    /**
     * 获取外部数据
     */
    protected fun getExtra() {}

    /**
     * 初始化视图
     */
    abstract fun initView()

    /**
     * 权限结果回调，接收权限请求结果
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults,
            this
        )   //将权限请求结果传递给EasyPermission库处理
    }

    /**
     * 权限申请失败回调
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        // 处理权限名字字符串
        val sb = StringBuffer()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }
        sb.replace(sb.length - 2, sb.length, "")
        //用户点击拒绝并不再询问时调用
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                .setPositiveButton("是")
                .setNegativeButton("否")
                .build()
                .show()
        }
    }

    /**
     * 权限申请成功回调
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

        Logger.t(TAG).d("onPermissionGranted, 获取成功的权限$perms")

    }
}