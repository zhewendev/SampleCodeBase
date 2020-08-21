package com.zhewen.eyepetizer.common.base

import android.content.Context
import android.os.Bundle
import com.zhewen.eyepetizer.common.R
import com.zhewen.eyepetizer.common.manager.ActivityManager
import com.zhewen.eyepetizer.common.widget.viewgroup.MultipleStateView
import me.yokeyword.fragmentation.SupportActivity
/**
* @author: zhewen
* created: 2020/8/19
* desc: Activity底层基类
*/
abstract class BaseSupportActivity: SupportActivity() {

    protected lateinit var mContext: Context
    protected lateinit var  mMultipleStateView: MultipleStateView

    /**
     * 跳转到其他Activity启动或者退出的模式
     */
    enum class TransitionMode{
        LEFT,RIGHT,TOP,BOTTOM,SCALE,FADE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overrideTransitionAnimation()
        initData()

    }

    private fun initData() {
        val extras = intent.extras
        if (extras != null) {
            getBundleExtras(extras)
        }
        mContext = this
        setContentView(getLayoutId())
    }

    override fun onStart() {
        super.onStart()
        ActivityManager.getInstance().addActivity(this)
    }

    /**
     * 设置activity进入动画 todo
     */
    private fun overrideTransitionAnimation() {
        if (isHasOverridePendingTransition()) {
            when(getOverridePendingTransition()) {
                TransitionMode.TOP -> overridePendingTransition(R.anim.top_in, R.anim.no_anim)
                TransitionMode.BOTTOM -> overridePendingTransition(R.anim.bottom_in, R.anim.no_anim)
                TransitionMode.LEFT -> overridePendingTransition(R.anim.left_in, R.anim.no_anim)
                TransitionMode.RIGHT -> overridePendingTransition(R.anim.right_in, R.anim.no_anim)
                TransitionMode.FADE -> overridePendingTransition(R.anim.fade_in, R.anim.no_anim)
                TransitionMode.SCALE -> overridePendingTransition(R.anim.scale_in, R.anim.no_anim)
            }
        }
    }

    override fun finish() {
        super.finish()
        if (isHasOverridePendingTransition()) {
            when (getOverridePendingTransition()) {
                TransitionMode.TOP -> overridePendingTransition(0, R.anim.top_out)
                TransitionMode.BOTTOM -> overridePendingTransition(0, R.anim.bottom_out)
                TransitionMode.LEFT -> overridePendingTransition(0, R.anim.left_out)
                TransitionMode.RIGHT -> overridePendingTransition(0, R.anim.right_out)
                TransitionMode.FADE -> overridePendingTransition(0, R.anim.fade_out)
                TransitionMode.SCALE -> overridePendingTransition(0, R.anim.scale_out)
            }
        }

    }


    /**
     * 是否有过渡动画
     */
    protected open fun isHasOverridePendingTransition() = false

    /**
     * 获得切换动画的模式
     */
    protected open fun getOverridePendingTransition(): TransitionMode? = null

    /**
     * 获取bundle中数据
     */
    open fun getBundleExtras(extras: Bundle) {}

    /**
     * 获取布局视图
     */
    abstract fun getLayoutId(): Int
}