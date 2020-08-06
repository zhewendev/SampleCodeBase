package com.zhewen.eyepetizer.common.base

open class BasePresenter<T : IBaseView> : IPresenter<T> {

    var mRootView: T? = null
        private set

    override fun attachView(mRootView: T) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        this.mRootView = null
    }

    private val isViewAttached: Boolean
        get() = mRootView != null


}