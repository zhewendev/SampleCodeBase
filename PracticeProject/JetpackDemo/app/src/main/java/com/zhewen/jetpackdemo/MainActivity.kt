package com.zhewen.jetpackdemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.util.Function
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.zhewen.jetpackdemo.lifecycle.MyObserver


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(MyObserver())
        val mutableLiveData = MutableLiveData<String>()
        mutableLiveData.observe(this) {
            Log.d(TAG, "onChanged:$it")
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        val transformedLiveData = Transformations.map(mutableLiveData) {name ->
            "${name}LiveData is great"
        }
        transformedLiveData.observe(this){
            Log.d(TAG,"onChange2$it")
        }
        mutableLiveData.postValue("Hello LiveData ")
    }
}