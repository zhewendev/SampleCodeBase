package com.zhewen.kotlinmvp.mvp.presenter

import com.zhewen.kotlinmvp.base.BasePresenter
import com.zhewen.kotlinmvp.mvp.contract.HomeContract
import com.zhewen.kotlinmvp.mvp.model.bean.HomeBean

class HomePresenter : BasePresenter<HomeContract.View>, HomeContract.Presenter {
    private var bannerHomeBean: HomeBean? = null

    private var nextPageUrl:String?=null     //加载首页的Banner 数据+一页数据合并后，nextPageUrl没 add


    override fun requestHomeData(num: Int) {
        TODO("Not yet implemented")
    }

    override fun loadMoreData() {
        TODO("Not yet implemented")
    }
}