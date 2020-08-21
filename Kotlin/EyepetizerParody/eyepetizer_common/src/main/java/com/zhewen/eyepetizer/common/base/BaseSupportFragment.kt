package com.zhewen.eyepetizer.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhewen.eyepetizer.common.widget.viewgroup.MultipleStateView
import me.yokeyword.fragmentation.SupportFragment

abstract class BaseSupportFragment: SupportFragment() {

    protected lateinit var mMultipleStateView: MultipleStateView

    companion object{
        const val TAG: String  = "BaseSupportFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            getBundleExtras(arguments!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(),null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
    }

    /**
     * 获取Bundle中相应的data
     */
    open fun getBundleExtras(extras: Bundle) {}

    /**
     * 获取布局资源id
     */
    abstract fun getLayoutId(): Int

    /**
     * 视图初始化
     */
    abstract fun initView(savedInstanceState: Bundle?)
}