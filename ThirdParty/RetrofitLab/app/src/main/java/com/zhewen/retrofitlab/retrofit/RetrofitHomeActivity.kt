package com.zhewen.retrofitlab.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zhewen.retrofitlab.R
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHomeActivity:AppCompatActivity(R.layout.activity_retrofit_home) {

    private val retrofit:Retrofit by lazy(LazyThreadSafetyMode.NONE) {
        Retrofit.Builder()
            .baseUrl("http://fy.iciba.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val apiService:ApiService by lazy(LazyThreadSafetyMode.NONE) {
        retrofit.create(ApiService::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        findViewById<TextView>(R.id.tv_first).setOnClickListener {
            val call = apiService.getCall()
            call?.enqueue(object :Callback<Translation?>{
                override fun onResponse(call: Call<Translation?>, response: Response<Translation?>) {
                    Log.d(TAG,"onResponse")
                    response.body()?.show()
                }

                override fun onFailure(call: Call<Translation?>, t: Throwable) {
                    println("网络请求失败")
                }

            })
        }
    }
    companion object {
        const val TAG = "RetrofitHomeActivity"
    }
}