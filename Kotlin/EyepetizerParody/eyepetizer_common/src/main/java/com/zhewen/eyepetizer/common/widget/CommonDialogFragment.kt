package com.zhewen.eyepetizer.common.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.orhanobut.logger.Logger

/**
 * 公共dialog
 */
abstract class CommonDialogFragment : DialogFragment() {

    var mRootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    abstract fun getLayoutId(): Int

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            manager.beginTransaction().remove(this).commit()
            super.show(manager, tag)
        } catch (err: Exception) {
            Logger.t(TAG).e("show error$err")
        }

    }

    override fun dismiss() {
        if (activity != null && !activity!!.isFinishing && !activity!!.isDestroyed) {
            super.dismiss()
            activity!!.finish()
        }
    }

    companion object {
        const val TAG: String = "CommonDialogFragment"
    }
}