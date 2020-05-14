package com.zhewen.kotlinmvp.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.zhewen.kotlinmvp.mvp.presenter.MultipleStatusView
import pub.devrel.easypermissions.EasyPermissions

/**
* @author: zhewen
* created: 2020/5/14
* desc: Base Activity
*/
abstract class BaseActivity : AppCompatActivity(),EasyPermissions.PermissionCallbacks{

    protected var mLayouStatusView : MultipleStatusView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initData()
        initView()
        start()
        initListener()
    }

    private fun initListener() {
        mLayouStatusView?.setOnClickListener(mRetryClickListener)
    }

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        start()
    }

    /**
     * load layout
     */
    abstract fun layoutId() : Int

    /**
     * init data
     */
    abstract fun initData()

    /**
     * init view
     */
    abstract fun initView()

    /**
     * start request
     */
    abstract fun start()

    /**
     * open the soft keyboard
     */
    fun openKeyBoard(mEditText: EditText,mContext:Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText,InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * close the soft keyboard
     */
    fun closeKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}