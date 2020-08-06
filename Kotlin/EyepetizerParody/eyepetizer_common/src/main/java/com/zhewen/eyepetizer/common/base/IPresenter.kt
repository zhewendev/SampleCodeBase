package com.zhewen.eyepetizer.common.base

/**
* @author: zhewen
* created: 2020/8/6
* desc:基类
*/
interface IPresenter<in V : IBaseView> {

    fun attachView(mRootView : V)

    fun detachView()
}