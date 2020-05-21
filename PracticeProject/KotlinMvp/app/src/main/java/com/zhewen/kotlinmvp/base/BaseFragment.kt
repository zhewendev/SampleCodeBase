package com.zhewen.kotlinmvp.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
import com.zhewen.kotlinmvp.mvp.presenter.MultipleStatusView
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

/**
 * @author: zhewen
 * created: 2020/5/15
 * desc: base Fragment
 */
abstract class BaseFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    /**
     * Whether the view is loaded
     */
    private var isViewPrepare = false   //todo

    /**
     * Whether the data is loaded
     */
    private var hasLoadData = false     //todo

    /**
     * View switching in multiple states
     */
    protected var mLayoutStateView: MultipleStatusView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(),null)
    }

    /**
     * load layout
     */
    @LayoutRes
    abstract fun getLayoutId():Int

    /**
     *  Initialize the view
     */
    abstract fun initView()

    /**
     * Lazy Load
     */
    abstract fun lazyLoad()

    /**
     * Permission application callback
     */
    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Logger.t("EasyPermissions").i("获取成功的权限$perms")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        //处理权限名字字符串
        val sb = StringBuffer()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }
        sb.replace(sb.length - 2, sb.length, "")
        //用户点击拒绝并不在询问时候调用
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(activity, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                .setPositiveButton("好")
                .setNegativeButton("不行")
                .build()
                .show()
        }
    }


}