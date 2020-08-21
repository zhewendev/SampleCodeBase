package com.zhewen.eyepetizer.common.widget.viewgroup

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.zhewen.eyepetizer.common.R

/**
 * @author: zhewen
 * created: 2020/8/19
 * desc:多状态切换布局，例如加载界面，空界面，错误界面，网络异常界面等等
 */
class MultipleStateView : RelativeLayout {

    private var mEmptyView: View? = null
    private var mErrorView: View? = null
    private var mNoNetworkView: View? = null
    private var mLoadingView: LoadingView? = null
    private var mContentViews: MutableList<View> = mutableListOf()
    private var mEmptyViewResId: Int = 0
    private var mErrorViewResId: Int = 0
    private var mNoNetworkViewResId: Int = 0


    enum class State {
        EMPTY, ERROR, LOADING, NO_NETWORK, CONTENT
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val a: TypedArray = context.obtainStyledAttributes(attrs,R.styleable.MultipleStateView)
        //todo 设置各状态视图默认的展示布局
        mEmptyViewResId = a.getResourceId(R.styleable.MultipleStateView_emptyView,R.layout.loading_view)
        mErrorViewResId = a.getResourceId(R.styleable.MultipleStateView_errorView,R.layout.loading_view)
        mNoNetworkViewResId = a.getResourceId(R.styleable.MultipleStateView_noNetWorkView,R.layout.loading_view)
        a.recycle()
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        super.addView(child, params)
        child?.let {
            if (child.tag != State.LOADING && child.tag != State.EMPTY && child.tag != State.NO_NETWORK && child.tag != State.ERROR) {
                mContentViews.add(child)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clear(mEmptyView,mLoadingView,mNoNetworkView,mErrorView)
    }

    fun showLoading() {
        switchContent(State.LOADING)
    }


    fun showEmpty(onClickListener: OnClickListener) {
        switchContent(State.EMPTY, onClickListener)
    }

    fun showError(onClickListener: OnClickListener) {
        switchContent(State.ERROR, onClickListener)
    }

    fun showNoNetwork(onClickListener: OnClickListener) {
        switchContent(State.NO_NETWORK, onClickListener)
    }

    fun showContent() {
        switchContent(State.CONTENT)
    }

    /**
     * 显示空数据视图
     */
    private fun showEmptyView(onClickListener: OnClickListener) {
        setEmptyView(onClickListener)
        hideLoadingView()
        hideNoNetworkView()
        hideErrorView()
        setContentViewVisible(false)

    }

    /**
     * 设置空数据视图
     */
    @SuppressLint("InflateParams")
    private fun setEmptyView(onClickListener: OnClickListener) {
        if (mEmptyView == null) {
            mEmptyView = LayoutInflater.from(context).inflate(mEmptyViewResId, null)
            mEmptyView?.tag = State.EMPTY
            mEmptyView?.setOnClickListener(onClickListener)
            addStateView(mEmptyView)
        } else {
            mEmptyView?.visibility = View.VISIBLE
        }
    }

    /**
     * 隐藏空数据视图
     */
    private fun hideEmptyView() {
        mEmptyView?.let {
            it.visibility = View.GONE
        }
    }

    /**
     * 显示界面加载中视图
     */
    private fun showLoadingView(onClickListener: OnClickListener) {
        setLoadingView(onClickListener)
        hideEmptyView()
        hideNoNetworkView()
        hideErrorView()
        setContentViewVisible(false)
    }

    /**
     * 设置加载中视图
     */
    private fun setLoadingView(onClickListener: OnClickListener) {
        if (mLoadingView == null) {
            mLoadingView = LoadingView(context)
            mLoadingView?.tag = State.LOADING
            mLoadingView?.setOnClickListener(onClickListener)
            addStateView(mLoadingView)
        } else {
            mLoadingView?.visibility = View.VISIBLE
            mLoadingView?.restartLoadingAnimator()
        }
    }

    /**
     * 隐藏加载中视图
     */
    private fun hideLoadingView() {
        mLoadingView?.let {
            it.stopLoadingAnimator()
            it.visibility = View.GONE
        }
    }

    /**
     * 显示无网络视图
     */
    private fun showNoNetworkView(onClickListener: OnClickListener) {
        setNoNetworkView(onClickListener)
        hideEmptyView()
        hideLoadingView()
        hideErrorView()
        setContentViewVisible(false)
    }

    /**
     * 设置无网络视图
     */
    @SuppressLint("InflateParams")
    private fun setNoNetworkView(onClickListener: OnClickListener) {
        if(mNoNetworkView == null) {
            mNoNetworkView = LayoutInflater.from(context).inflate(mNoNetworkViewResId,null)
            mNoNetworkView?.tag = State.NO_NETWORK
            mNoNetworkView?.setOnClickListener(onClickListener)
            addStateView(mNoNetworkView)
        } else {
            mNoNetworkView?.visibility = View.VISIBLE
        }
    }

    /**
     * 隐藏网络视图
     */
    private fun hideNoNetworkView() {
        mNoNetworkView?.let {
            it.visibility = View.GONE
        }
    }

    /**
     * 展示错误视图
     */
    private fun showErrorView(onClickListener: OnClickListener) {
        setErrorView(onClickListener)
        hideEmptyView()
        hideLoadingView()
        hideNoNetworkView()
        setContentViewVisible(false)
    }

    /**
     * 设置错误视图
     */
    @SuppressLint("InflateParams")
    private fun setErrorView(onClickListener: OnClickListener) {
        if (mErrorView == null) {
            mErrorView = LayoutInflater.from(context).inflate(mErrorViewResId, null)
            mErrorView?.tag = State.ERROR
            mErrorView?.setOnClickListener(onClickListener)
            addStateView(mErrorView)
        } else {
            mErrorView?.visibility = View.VISIBLE
        }
    }

    /**
     * 隐藏错误视图
     */
    private fun hideErrorView() {
        mErrorView?.let {
            it.visibility = View.GONE
        }
    }

    /**
     * 显示内容视图
     */
    private fun showContentView() {
        hideEmptyView()
        hideLoadingView()
        hideNoNetworkView()
        hideErrorView()
        setContentViewVisible(true)
    }

    /**
     * 设置内容界面是否显示
     */
    private fun setContentViewVisible(isVisible: Boolean) {
        for (contentView in mContentViews) {
            contentView.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }

    private fun addStateView(view: View?) {
        val layoutParams =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layoutParams.addRule(CENTER_IN_PARENT)
        addView(view, layoutParams)
    }

    /**
     * 切换布局
     */
    private fun switchContent(
        state: State,
        onClickListener: OnClickListener = OnClickListener { }
    ) {
        when (state) {
            State.EMPTY -> showEmptyView(onClickListener)
            State.LOADING -> showLoadingView(onClickListener)
            State.NO_NETWORK -> showNoNetworkView(onClickListener)
            State.ERROR -> showErrorView(onClickListener)
            State.CONTENT -> showContentView()
        }
    }

    private fun clear(vararg views: View?) {
        try {
            for (view in views) {
                view?.let {
                    removeView(view)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}


