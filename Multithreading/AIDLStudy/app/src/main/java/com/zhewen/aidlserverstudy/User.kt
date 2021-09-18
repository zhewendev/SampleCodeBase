package com.zhewen.aidlserverstudy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var age: Int,
    var name: String
): Parcelable
