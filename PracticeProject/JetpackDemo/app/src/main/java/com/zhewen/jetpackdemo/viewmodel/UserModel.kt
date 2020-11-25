package com.zhewen.jetpackdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserModel:ViewModel() {
    val mUserLiveData:MutableLiveData<User> = MutableLiveData()
    init {
        //模拟从网络加载用户数据
        mUserLiveData.postValue(User(1,"name1"))
    }
    fun doSomething(){
        val user = mUserLiveData.value
        if (user != null){
            user.age = 15
            user.name = "name15"
            mUserLiveData.value = user
        }
    }


}