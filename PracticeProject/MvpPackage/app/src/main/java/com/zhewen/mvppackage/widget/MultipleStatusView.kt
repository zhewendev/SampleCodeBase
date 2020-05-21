package com.zhewen.mvppackage.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

/**
 * @author: zhewen
 * created: 2020/5/21
 * desc:A view that facilitates switching in multiple states
 */
class MultipleStatusView(context: Context?, attrs: AttributeSet?, defStyle: Int) :
    RelativeLayout(context, attrs, defStyle) {

    companion object {
        const val STATUS_CONTENT: Int = 0x00
        const val STATUS_LOADING: Int = 0x01
        const val STATUS_EMPTY: Int = 0x02
        const val STATUS_ERROR: Int = 0x03
        const val STATUS_NO_NETWORK: Int = 0x04

        const val NULL_RESOURCE_ID: Int = -1
    }

    private var mEmptyView: View? = null
    private var mErrorView: View? = null
    private var mLoadingView: View? = null
    private var mNoNetworking: View? = null
    private var mContentView: View? = null

    private val mEmptyViewResId: Int
    private val mErrorViewResId: Int
    private val mLoadingViewResId: Int
    private val mNoNetworkViewResId: Int
    private val mContentViewResId: Int

    init {
        
    }



}