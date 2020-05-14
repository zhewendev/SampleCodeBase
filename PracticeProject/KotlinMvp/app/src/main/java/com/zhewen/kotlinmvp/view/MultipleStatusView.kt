package com.zhewen.kotlinmvp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.zhewen.kotlinmvp.R
import java.util.*

/**
 * @author: zhewen
 * created: 2020/5/14
 * desc: A view that facilitates switching in multiple states
 */
//todo
class MultipleStatusView constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var mEmptyView: View? = null
    private var mErrorView: View? = null
    private var mLoadingView: View? = null
    private var mNoNetworkView: View? = null
    private var mContentView: View? = null
    private val mEmptyViewResId: Int
    private val mErrorViewResId: Int
    private val mLoadingViewResId: Int
    private val mNoNetworkViewResId: Int
    private val mContentViewResId: Int
    /**
     * 获取当前状态
     */
    var viewStatus = 0
        private set
    private var mInflater: LayoutInflater?
    private var mOnRetryClickListener: OnClickListener? = null
    private val mOtherIds: ArrayList<Int>? = ArrayList()
    override fun onFinishInflate() {
        super.onFinishInflate()
        showContent()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clear(mEmptyView!!, mLoadingView!!, mErrorView!!, mNoNetworkView!!)
        mOtherIds?.clear()
        if (null != mOnRetryClickListener) {
            mOnRetryClickListener = null
        }
        mInflater = null
    }

    fun showContent() {
        viewStatus = STATUS_CONTENT
        if (null == mContentView && mContentViewResId != NULL_RESOURCE_ID) {
            mContentView = mInflater!!.inflate(mContentViewResId, null)
            addView(
                mContentView,
                0,
                DEFAULT_LAYOUT_PARAMS
            )
        }
        showContentView()
    }

    private fun showContentView() {
        val childCount : Int = childCount
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.visibility = if (mOtherIds!!.contains(view.id)) View.GONE else View.VISIBLE
        }
    }


    private fun clear(vararg views: View) {
        if (null == views) {
            return
        }
        try {
            for (view in views) {
                removeView(view)
            }
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val TAG = "MultipleStatusView"
        private val DEFAULT_LAYOUT_PARAMS =
            LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
        const val STATUS_CONTENT = 0x00
        const val STATUS_LOADING = 0x01
        const val STATUS_EMPTY = 0x02
        const val STATUS_ERROR = 0x03
        const val STATUS_NO_NETWORK = 0x04
        const val NULL_RESOURCE_ID = -1
    }

    init {
        val a =
            context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView, defStyleAttr, 0)
        mEmptyViewResId =
            a.getResourceId(R.styleable.MultipleStatusView_emptyView, R.layout.empty_view)
        mErrorViewResId =
            a.getResourceId(R.styleable.MultipleStatusView_errorView, R.layout.error_view)
        mLoadingViewResId =
            a.getResourceId(R.styleable.MultipleStatusView_loadingView, R.layout.loading_view)
        mNoNetworkViewResId =
            a.getResourceId(R.styleable.MultipleStatusView_noNetworkView, R.layout.no_network_view)
        mContentViewResId = a.getResourceId(
            R.styleable.MultipleStatusView_contentView,
            MultipleStatusView.NULL_RESOURCE_ID
        )
        a.recycle()
        mInflater = LayoutInflater.from(getContext())
    }
}