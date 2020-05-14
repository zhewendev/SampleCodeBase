package com.zhewen.kotlinmvp.base


/**
* @author: zhewen
* created: 2020/5/14
* desc: base presenter
*/


interface IPresenter<in V: IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}
