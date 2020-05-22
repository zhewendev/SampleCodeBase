package com.zhewen.mvppackage.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.zhewen.mvppackage.R

/**
 * @author: zhewen
 * created: 2020/5/21
 * desc:A view that facilitates switching in multiple states
 */
class MultipleStatusView(context: Context, attrs: AttributeSet? = null, defStyle: Int) :
    RelativeLayout(context, attrs, defStyle) {

    companion object {
        private val TAG: String = MultipleStatusView::class.java.simpleName
        const val STATUS_CONTENT: Int = 0x00
        const val STATUS_LOADING: Int = 0x01
        const val STATUS_EMPTY: Int = 0x02
        const val STATUS_ERROR: Int = 0x03
        const val STATUS_NO_NETWORK: Int = 0x04

        const val NULL_RESOURCE_ID: Int = -1
        private val DEFAULT_LAYOUT_PARAMS: LayoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
    }

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

    private var mInflater: LayoutInflater?
    private var mOnRetryClickListener: OnClickListener? = null
    private val mOtherIds: ArrayList<Int> = ArrayList()

    /**
     * 获取当前状态
     */
    var mViewStatus: Int = 0
        private set

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView, defStyle, 0)
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
            NULL_RESOURCE_ID
        )
        a.recycle()
        mInflater = LayoutInflater.from(getContext())
    }

    // 当View中所有的子控件均被映射成xml后触发
    override fun onFinishInflate() {
        super.onFinishInflate()
        showContent()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clear(mEmptyView!!, mLoadingView!!, mErrorView!!, mNoNetworkView!!)
        mOtherIds.clear()
        if (null != mOnRetryClickListener) {
            mOnRetryClickListener = null
        }
        mInflater = null
    }

    /**
     * 设置重试点击事件
     *
     * @param onRetryClickListener 重试点击事件
     */
    fun setOnRetryClickListener(onRetryClickListener: OnClickListener?) {
        mOnRetryClickListener = onRetryClickListener
    }

    /**
     * 显示空视图
     */
    fun showEmpty() {
        showEmpty(mEmptyViewResId, DEFAULT_LAYOUT_PARAMS)
    }

    fun showEmpty(layoutId: Int, layoutParams: ViewGroup.LayoutParams) {
        showEmpty(inflateView(layoutId), layoutParams)
    }

    fun showEmpty(view: View, layoutParams: ViewGroup.LayoutParams) {
        checkNull(view, "Empty view is null!")
        mViewStatus = STATUS_EMPTY
        if (null == mEmptyView) {
            mEmptyView = view
            val emptyRetryView =
                mEmptyView!!.findViewById<View>(R.id.empty_retry_view)
            if (null != mOnRetryClickListener && null != emptyRetryView) {
                emptyRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds.add(mEmptyView!!.id)
            addView(mEmptyView, 0, layoutParams)
        }
        showViewById(mEmptyView!!.id)
    }

    /**
     * 显示错误视图
     */
    @JvmOverloads
    fun showError(
        layoutId: Int = mErrorViewResId,
        layoutParams: ViewGroup.LayoutParams? = DEFAULT_LAYOUT_PARAMS
    ) {
        showError(inflateView(layoutId), layoutParams)
    }

    fun showError(view: View?, layoutParams: ViewGroup.LayoutParams?) {
        checkNull(view, "Error view is null!")
        mViewStatus = STATUS_ERROR
        if (null == mErrorView) {
            mErrorView = view
            val errorRetryView =
                mErrorView!!.findViewById<View>(R.id.error_retry_view)
            if (null != mOnRetryClickListener && null != errorRetryView) {
                errorRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds.add(mErrorView!!.id)
            addView(mErrorView, 0, layoutParams)
        }
        showViewById(mErrorView!!.id)
    }

    /**
     * 显示加载中视图
     */
    @JvmOverloads
    fun showLoading(
        layoutId: Int = mLoadingViewResId,
        layoutParams: ViewGroup.LayoutParams? = DEFAULT_LAYOUT_PARAMS
    ) {
        showLoading(inflateView(layoutId), layoutParams)
    }

    fun showLoading(view: View?, layoutParams: ViewGroup.LayoutParams?) {
        checkNull(view, "Loading view is null!")
        mViewStatus = STATUS_LOADING
        if (null == mLoadingView) {
            mLoadingView = view
            mOtherIds.add(mLoadingView!!.id)
            addView(mLoadingView, 0, layoutParams)
        }
        showViewById(mLoadingView!!.id)
    }

    /**
     * 显示无网络视图
     */
    @JvmOverloads
    fun showNoNetwork(
        layoutId: Int = mNoNetworkViewResId,
        layoutParams: ViewGroup.LayoutParams? = DEFAULT_LAYOUT_PARAMS
    ) {
        showNoNetwork(inflateView(layoutId), layoutParams)
    }

    fun showNoNetwork(
        view: View?,
        layoutParams: ViewGroup.LayoutParams?
    ) {
        checkNull(view, "No network view is null!")
        mViewStatus =
            STATUS_NO_NETWORK
        if (null == mNoNetworkView) {
            mNoNetworkView = view
            val noNetworkRetryView =
                mNoNetworkView!!.findViewById<View>(R.id.no_network_retry_view)
            if (null != mOnRetryClickListener && null != noNetworkRetryView) {
                noNetworkRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds.add(mNoNetworkView!!.id)
            addView(mNoNetworkView, 0, layoutParams)
        }
        showViewById(mNoNetworkView!!.id)
    }


    private fun showContent() {
        mViewStatus = STATUS_CONTENT
        if (null == mContentView && mContentViewResId != NULL_RESOURCE_ID) {
            mContentView = mInflater!!.inflate(mContentViewResId, null)
            addView(mContentView, 0, DEFAULT_LAYOUT_PARAMS)
        }
    }

    private fun showContentView() {
        val childCount: Int = childCount
        for (i in 0 until childCount) {
            val view: View = getChildAt(i)
            view.visibility = if (mOtherIds.contains(view.id)) View.GONE else View.VISIBLE
        }
    }

    private fun showViewById(viewId: Int) {
        val childCount = childCount
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.visibility = if (view.id == viewId) View.VISIBLE else View.GONE
        }
    }

    private fun inflateView(layoutId: Int): View {
        return mInflater!!.inflate(layoutId, null)
    }

    private fun checkNull(`object`: Any?, hint: String) {
        if (null == `object`) {
            throw NullPointerException(hint)
        }
    }

    private fun clear(vararg views: View) {
        try {
            for (view in views) {
                removeView(view)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}