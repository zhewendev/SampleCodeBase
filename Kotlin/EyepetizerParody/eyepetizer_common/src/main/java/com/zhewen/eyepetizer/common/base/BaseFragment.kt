package com.zhewen.eyepetizer.common.base

import android.view.View
import pub.devrel.easypermissions.EasyPermissions
/**
* @author: zhewen
* created: 2020/8/20
* desc: 基础Fragment
*/
abstract class BaseFragment : BaseSupportFragment(),EasyPermissions.PermissionCallbacks {


    fun showLoading() {
        mMultipleStateView.showLoading()
    }

    fun showEmpty(onClickListener: View.OnClickListener) {
        mMultipleStateView.showEmpty(onClickListener)
    }

    fun showNoNetwork(onClickListener: View.OnClickListener) {
        mMultipleStateView.showNoNetwork(onClickListener)
    }

    fun showError(onClickListener: View.OnClickListener) {
        mMultipleStateView.showError(onClickListener)
    }

    fun showContent() {
        mMultipleStateView.showContent()
    }
}