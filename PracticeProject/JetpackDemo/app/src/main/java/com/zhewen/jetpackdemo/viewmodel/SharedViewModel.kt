package com.zhewen.jetpackdemo.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class SharedViewModel:ViewModel() {
    private val selected:MutableLiveData<String> = MutableLiveData()
    fun select(string: String){
        selected.value = string
    }

    fun getSelected():LiveData<String>{
        return selected
    }
}

//class MasterFragment : Fragment() {
//    private var model: SharedViewModel? = null
//    fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        model = activity?.let { ViewModelProvider(it)[SharedViewModel::class.java] }
//        stringSelector.setOnClickListener { item -> model!!.select(item) }
//    }
//}
//
//
//class DetailFragment: Fragment(){
//    private var model:SharedViewModel? = null
//    fun onCreate(savedInstanceState: Bundle?){
//        super.onCreate(savedInstanceState)
//        model = activity?.let { ViewModelProvider(it)[SharedViewModel::class.java] }
//        model?.getSelected()?.observe(this,{
//            //更新UI
//        })
//    }
//}