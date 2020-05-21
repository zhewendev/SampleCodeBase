package com.zhewen.mvppackage.base
/**
* @author: zhewen
* created: 2020/5/21
* desc: base presenter interface
*/
interface IPresenter<in V: IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}