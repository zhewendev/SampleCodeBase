package com.zhewen.aidlserverstudy

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var age: Int = 0,
    var name: String = ""
):Parcelable {

    fun readFromParcel(parcel: Parcel) {
        this.name = parcel.readString().toString()
        this.age = parcel.readInt()
    }
}
