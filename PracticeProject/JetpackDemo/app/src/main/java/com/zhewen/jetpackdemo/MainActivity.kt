package com.zhewen.jetpackdemo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.zhewen.jetpackdemo.viewmodel.UserModel


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

//    private lateinit var mutableLiveData1:MutableLiveData<String>
//    private lateinit var mutableLiveData2:MutableLiveData<String>
////    private lateinit var liveDataSwitch:MutableLiveData<Boolean>
//    private lateinit var liveDataManager:MediatorLiveData<String>
    private lateinit var text:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.tv_text)
//        lifecycle.addObserver(MyObserver())
//        val mutableLiveData = MutableLiveData<String>()
//        mutableLiveData.observe(this) {
//            Log.d(TAG, "onChanged:$it")
//            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
//        }
//        val transformedLiveData = Transformations.map(mutableLiveData) {name ->
//            "${name}LiveData is great"
//        }
//        transformedLiveData.observe(this){
//            Log.d(TAG,"onChange2$it")
//        }
//        mutableLiveData.postValue("Hello LiveData ")
//        mutableLiveData1 = MutableLiveData()
//        mutableLiveData2 = MutableLiveData()
//        liveDataManager = MediatorLiveData()
//        liveDataManager.addSource(mutableLiveData1){
//            Log.d(TAG,"onChange1:$it")
//        }
//
//        liveDataManager.addSource(mutableLiveData2){
//            Log.d(TAG,"onChange2:$it")
//        }
//        liveDataManager.observe(this){
//            Log.d(TAG,"onChanged:$it")
//        }
//
//        mutableLiveData1.postValue("LiveData init")
//        mutableLiveData2.postValue("LiveData developer")
//
//        text.setOnClickListener(){
//            mutableLiveData1.postValue("change LiveData1 data")
//        }
        val userModel: UserModel = ViewModelProvider(this)[UserModel::class.java]
        userModel.mUserLiveData.observe(this){
            text.text = it.toString()
        }

        text.setOnClickListener(){
            userModel.doSomething()
        }

    }
}