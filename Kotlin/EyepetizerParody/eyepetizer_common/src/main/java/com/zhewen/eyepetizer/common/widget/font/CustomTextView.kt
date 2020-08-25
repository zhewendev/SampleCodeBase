package com.zhewen.eyepetizer.common.widget.font

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class CustomTextView: AppCompatTextView {

    constructor(context: Context): this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context,attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context,attrs,defStyleAttr) {
        if (!isInEditMode) {

        }
    }
}