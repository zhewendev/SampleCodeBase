package com.zhewen.kotlinmvp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.zhewen.kotlinmvp.mvp.presenter.MultipleStatusView
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
}