package com.zhewen.eyepetizer.common.base

interface IPresenter<in V : IBaseView> {

    fun attachView(mRootView : V)

}