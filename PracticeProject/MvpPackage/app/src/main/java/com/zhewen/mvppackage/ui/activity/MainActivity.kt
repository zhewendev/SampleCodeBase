package com.zhewen.mvppackage.ui.activity

import android.os.Bundle
import com.zhewen.mvppackage.R
import com.zhewen.mvppackage.base.BaseActivity

class MainActivity :BaseActivity() {

    private val mTitles = arrayOf("每日精选", "发现", "热门", "我的")
    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected)

    private var mHomeFragment: HomeFragment? = null
//    private var mDiscoveryFragment: DiscoveryFragment? = null
//    private var mHotFragment: HotFragment? = null
//    private var mMineFragment: MineFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
    }

    override fun layoutId(): Int = R.layout.activity_main

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun start() {
        TODO("Not yet implemented")
    }
}
