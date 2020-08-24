package com.zhewen.eyepetizer.common.utils

import android.view.View
import com.zhewen.eyepetizer.common.render.IRenderView

/**
 * @author: zhewen
 * created: 2020/8/24
 * desc:测量工具类
 */
class MeasureHelper(view: View) {

    private var mVideoViewWidth = 0     //视频布局的宽度
    private var mVideoViewHeight = 0    //视频布局的高度
    private var mVideoRealWidth = 0     //视频的实际宽度
    private var mVideoRealHeight = 0    //视频实际高度
    private var mVideoRotationDegree = 0 //屏幕旋转度数

    var mVideoMeasureWidth = 0  //测量后的宽度
    var mVideoMeasureHeight = 0 //测量后的高度
    private var mCurrentAspectRatio = IRenderView.AR_ASPECT_FIT_PARENT //宽高比例


    /**
     * 当 View.onMeasure(int, int) 时调用，用于输出视频校正后的宽高
     */
    fun doMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        var localWidthMeasureSpec = widthMeasureSpec
        var localHeightMeasureSpec = heightMeasureSpec

        //屏幕横屏处理
        if (mVideoRotationDegree == 90 || mVideoRotationDegree == 270) {
            localWidthMeasureSpec = heightMeasureSpec
            localHeightMeasureSpec = widthMeasureSpec
        }
        //获取父布局宽高
        var width = View.getDefaultSize(mVideoViewWidth, localWidthMeasureSpec)
        var height = View.getDefaultSize(mVideoViewHeight, localHeightMeasureSpec)
        //视频展示比例是匹配父布局，则宽高不变
        if (mCurrentAspectRatio == IRenderView.AR_MATCH_PARENT) {
            width = localWidthMeasureSpec
            height = localHeightMeasureSpec
        } else if (mVideoViewWidth > 0 && mVideoViewHeight > 0) {       //设置的视频宽高大于0时
            val widthSpecMode = View.MeasureSpec.getMode(localWidthMeasureSpec)
            val widthSpecSize = View.MeasureSpec.getSize(localWidthMeasureSpec)
            val heightSpecMode = View.MeasureSpec.getMode(localHeightMeasureSpec)
            val heightSpecSize = View.MeasureSpec.getSize(localHeightMeasureSpec)

            //当前控件测量模式为包裹模式时，根据视频比例与渲染比例，计算出视频显示的宽高比
            if (widthSpecMode == View.MeasureSpec.AT_MOST && heightSpecMode == View.MeasureSpec.AT_MOST) {
                val specAspectRatio =
                    widthSpecSize.toFloat() / heightSpecSize.toFloat()    //给定视频的宽高比
                var displayAspectRatio: Float   //视频显示的宽高比

                when (mCurrentAspectRatio) {
                    IRenderView.AR_16_9_FIT_PARENT -> {         //宽高 16：9
                        displayAspectRatio = 16.0f / 9.0f
                        if (mVideoRotationDegree == 90 || mVideoRotationDegree == 270)
                            displayAspectRatio = 1.0f / displayAspectRatio
                    }
                    IRenderView.AR_4_3_FIT_PARENT -> {          //宽高4：3
                        displayAspectRatio = 4.0f / 3.0f
                        if (mVideoRotationDegree == 90 || mVideoRotationDegree == 270)
                            displayAspectRatio = 1.0f / displayAspectRatio
                    }
                    IRenderView.AR_ASPECT_FIT_PARENT,           //适应、填充、包裹父布局
                    IRenderView.AR_ASPECT_FILL_PARENT,
                    IRenderView.AR_ASPECT_WRAP_CONTENT -> {
                        displayAspectRatio = mVideoViewWidth.toFloat() / mVideoViewHeight.toFloat()
                        if (mVideoRealWidth > 0 && mVideoRealHeight > 0)
                            displayAspectRatio =
                                displayAspectRatio * mVideoRealWidth / mVideoRealHeight
                    }
                    else -> {
                        displayAspectRatio = mVideoViewWidth.toFloat() / mVideoViewHeight.toFloat()
                        if (mVideoRealWidth > 0 && mVideoRealHeight > 0)
                            displayAspectRatio =
                                displayAspectRatio * mVideoRealWidth / mVideoRealHeight
                    }
                }


                val shouldBeWider = displayAspectRatio > specAspectRatio
                when (mCurrentAspectRatio) {
                    //以下三种模式，以大的为固定边，小的边按比例缩放
                    IRenderView.AR_ASPECT_FIT_PARENT,
                    IRenderView.AR_16_9_FIT_PARENT,
                    IRenderView.AR_4_3_FIT_PARENT -> {
                        if (shouldBeWider) {    //高度偏小，宽度不变，校正高度
                            width = widthMeasureSpec
                            height = (width / displayAspectRatio).toInt()
                        } else {
                            height = heightMeasureSpec
                            width = (height / displayAspectRatio).toInt()
                        }
                    }
                    //填充模式下，以小的为固定边，大的边按比例缩放
                    IRenderView.AR_ASPECT_FILL_PARENT -> {
                        if (shouldBeWider) {    //高度偏小，高度不变，校正宽度
                            height = heightSpecSize
                            width = (height * displayAspectRatio).toInt()
                        } else {
                            width = widthSpecSize
                            height = (width / displayAspectRatio).toInt()
                        }
                    }
                    //包裹模式，以视频宽高为准
                    IRenderView.AR_ASPECT_WRAP_CONTENT -> {
                        if (shouldBeWider) {    //高度偏小，将视频宽度与父布局宽度进行比较，取最小。并重新计算高度
                            width = mVideoRealWidth.coerceAtMost(widthSpecSize)
                            height = (width / displayAspectRatio).toInt()
                        } else {
                            height = mVideoRealHeight.coerceAtMost(heightSpecSize)
                            width = (height * displayAspectRatio).toInt()
                        }
                    }
                    else -> if (shouldBeWider) {
                        width = mVideoRealWidth.coerceAtMost(widthSpecSize)
                        height = (width / displayAspectRatio).toInt()
                    } else {
                        height = mVideoRealHeight.coerceAtMost(heightSpecSize)
                        width = (height * displayAspectRatio).toInt()
                    }
                }
            }

            //视频宽高为准确模式下,以小的为固定边，大的边按比例缩放
            else if (widthSpecMode == View.MeasureSpec.EXACTLY && heightSpecMode == View.MeasureSpec.EXACTLY) {
                width = widthSpecSize
                height = heightSpecSize
                if (mVideoViewWidth * height > width * mVideoViewHeight) {
                    // 宽度过大，校正宽度
                    width = height * mVideoViewWidth / mVideoViewHeight
                } else {
                    height = width * mVideoViewHeight / mVideoViewWidth
                }
            }
            //只有宽在准确模式下，宽度以实际为准，高度不能超过父布局
            else if (widthSpecMode == View.MeasureSpec.EXACTLY) {
                width = widthSpecSize
                height = width * mVideoViewHeight / mVideoViewWidth
                if (heightSpecMode == View.MeasureSpec.AT_MOST && height > heightSpecSize) {
                    height = heightSpecSize
                }
            }
            //只有高在准确模式模式下，高度以实际为准，宽度不能超过父布局
            else if (heightSpecMode == View.MeasureSpec.EXACTLY) {
                height = heightSpecSize
                width = height * mVideoViewWidth / mVideoViewHeight
                if (widthSpecMode == View.MeasureSpec.AT_MOST && width > widthSpecSize) {
                    width = widthSpecSize
                }
            }
            //当宽高都不固定时，使用实际的视频宽高
            else {
                width = mVideoViewWidth
                height = mVideoViewHeight
                if (heightSpecMode == View.MeasureSpec.AT_MOST && height > heightSpecSize) {
                    //太高，重新校正宽高
                    height = heightSpecSize
                    width = height * mVideoViewWidth / mVideoViewHeight
                }
                if (widthSpecMode == View.MeasureSpec.AT_MOST && width > widthSpecSize) {
                    //太宽，重新校正宽高
                    width = widthSpecSize
                    height = width * mVideoViewHeight / mVideoViewWidth
                }
            }

        }
        mVideoMeasureWidth = width
        mVideoMeasureHeight = height
    }


    /**
     * 设置视频宽高
     */
    fun setVideoSize(videoViewWidth: Int, videoViewHeight: Int) {
        mVideoViewWidth = videoViewWidth
        mVideoViewHeight = videoViewHeight
    }

    /**
     * 设置视频的旋转度数
     */
    fun setVideoRotation(videoRotationDegree: Int) {
        mVideoRotationDegree = videoRotationDegree
    }

    /**
     * 设置视频的宽高比例
     */
    fun setAspectRatio(aspectRatio: Int) {
        mCurrentAspectRatio = aspectRatio
    }

    /**
     * 设置视频的实际宽高
     */

    fun setVideoRealSize(videoRealWidth: Int, videoRealHeight: Int) {
        mVideoRealWidth = videoRealWidth
        mVideoRealHeight = videoRealHeight
    }
}