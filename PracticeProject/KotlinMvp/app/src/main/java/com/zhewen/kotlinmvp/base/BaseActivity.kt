package com.zhewen.kotlinmvp.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.zhewen.kotlinmvp.MyApplication
import com.zhewen.kotlinmvp.mvp.presenter.MultipleStatusView
import pub.devrel.easypermissions.AppSettingsDialog
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

    /**
     *  Rewrite the Activity or Fragment onRequestPermissionsResult() method to apply for permission
     *  Call EasyPermissions.onRequestPermissionsResult() inside, implement callback.
     */
    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * callback performed when the permission application fails
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        //  Processing permission name string
        val sb = StringBuffer()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }
        sb.replace(sb.length - 2, sb.length, "")
        // The user clicks reject and does not call when asked
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                .setPositiveButton("好")
                .setNegativeButton("不行")
                .build()
                .show()
        }


    }
}