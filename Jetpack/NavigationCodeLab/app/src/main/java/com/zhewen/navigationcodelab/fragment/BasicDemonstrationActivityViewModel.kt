package com.zhewen.navigationcodelab.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BasicDemonstrationActivityViewModel:ViewModel() {
    var sharedData = MutableLiveData<String>()
}