package com.zhewen.eyepetizer.common.widget.viewgroup

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.zhewen.eyepetizer.common.R
import kotlinx.android.synthetic.main.loading_view.view.*

/**
 * @author: zhewen
 * created: 2020/8/20
 * desc: 加载视图
 */
class LoadingView : FrameLayout {

    private lateinit var mRotationAnimator: ObjectAnimator

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        LayoutInflater.from(context).inflate(R.layout.loading_view, this)
        startLoadingAnimator()
    }

    private fun startLoadingAnimator() {
        mRotationAnimator = ObjectAnimator.ofFloat(img_loading, "rotation", 0f, 360f)
        mRotationAnimator.duration = 1000
        mRotationAnimator.repeatCount = -1
        mRotationAnimator.start()
    }

    fun restartLoadingAnimator() {
        if (!mRotationAnimator.isStarted) {
            mRotationAnimator.start()
        }
    }

    fun stopLoadingAnimator() {
        mRotationAnimator.cancel()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mRotationAnimator.cancel()
    }
}