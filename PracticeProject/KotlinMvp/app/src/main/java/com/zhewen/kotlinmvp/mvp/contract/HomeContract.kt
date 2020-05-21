package com.zhewen.kotlinmvp.mvp.contract

import com.zhewen.kotlinmvp.base.IBaseView
import com.zhewen.kotlinmvp.mvp.model.bean.HomeBean

/**
 * @author: zhewen
 * created: 2020/5/19
 * desc: HomeFragment Contract
 */
interface HomeContract {

    interface View : IBaseView {

        fun setHomeData(homeBean: HomeBean)

        fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(msg: String, errorCode: Int)
    }


    interface Presenter {

        fun requestHomeData(num: Int)

        fun loadMoreData()


    }
}