package com.zhewen.eyepetizer.common.widget

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.*
import android.widget.TextView
import com.zhewen.eyepetizer.common.R
import kotlinx.android.synthetic.main.common_dialog.*

open class CommonDialog : Dialog {

    private lateinit var mTitleName: TextView

    constructor(context: Context): super(context,0) {
        initSetting()
    }

    constructor(context: Context, themeResId: Int) : super(context, themeResId) {
        initSetting()
    }

    @SuppressLint("InflateParams")
    private fun initSetting() {
        window?.requestFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(context.resources.getDrawable(R.drawable.common_dialog_background,null))
        setCanceledOnTouchOutside(false)
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.common_dialog, null)
        window?.setContentView(view)
        window?.setLayout(
            context.resources.getDimensionPixelSize(R.dimen.dp_280),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        window?.setGravity(Gravity.CENTER)

        tv_title.text = "hisis"
    }


}