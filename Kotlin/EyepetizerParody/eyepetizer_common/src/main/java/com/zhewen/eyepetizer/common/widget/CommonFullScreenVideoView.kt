package com.zhewen.eyepetizer.common.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView
import com.zhewen.eyepetizer.common.utils.MeasureHelper

/**
 * @author: zhewen
 * created: 2020/8/24
 * desc:自定义全屏VideoView
 */
class CommonFullScreenVideoView : VideoView {

    private val mMeasureHelper: MeasureHelper = MeasureHelper(this)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mMeasureHelper.doMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(mMeasureHelper.mVideoMeasureWidth, mMeasureHelper.mVideoMeasureHeight)
    }

    /**
     * 设置视频比例
     */
    fun setAspectRatio(aspectRation: Int) {
        mMeasureHelper.setAspectRatio(aspectRation)
        requestLayout()
    }
}