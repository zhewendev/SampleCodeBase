package com.zhewen.retrofitlab.retrofit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zhewen.retrofitlab.R
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitHomeActivity:AppCompatActivity(R.layout.activity_retrofit_home) {

    private lateinit var mMessageTv :TextView
    private val retrofit:Retrofit by lazy(LazyThreadSafetyMode.NONE) {
        Retrofit.Builder()
            .baseUrl("https://api.uomg.com/")
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
        mMessageTv = findViewById(R.id.message_show)
        findViewById<TextView>(R.id.tv_first).setOnClickListener {
            val call = apiService.getMusicData("新歌榜","json")
            call?.enqueue(object :Callback<MusicData?>{
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<MusicData?>, response: Response<MusicData?>) {
                    Log.d(TAG,"onResponse")
                    val body = response.body() ?: return
                    val info = body.data
                    mMessageTv.text = info.name + info.url + info.picUrl
                }

                override fun onFailure(call: Call<MusicData?>, t: Throwable) {
                    println("网络请求失败")
                    Toast.makeText(this@RetrofitHomeActivity,"网络请求失败",Toast.LENGTH_SHORT).show()
                }

            })
        }
        val params: MutableMap<String, String> = HashMap()
        params["key"] = "myfittinglife"
        params["password"] = "123456"
        val postBodyBean = PostBodyBean("myfittinglife", 1, true)

        val textType: MediaType? = MediaType.parse("text/plain")
        val name = RequestBody.create(textType, "Carson")
        val age = RequestBody.create(textType, "24")
        val file = RequestBody.create(MediaType.parse("application/octet-stream"), "这里是模拟文件的内容")

        // @Part
        val filePart = MultipartBody.Part.createFormData("file", "test.txt", file)
        val call3: Call<ResponseBody> = service.testFileUpload1(name, age, filePart)
        ResponseBodyPrinter.printResponseBody(call3)

// @PartMap
// 实现和上面同样的效果

// @PartMap
// 实现和上面同样的效果
        val fileUpload2Args: MutableMap<String, RequestBody> = HashMap()
        fileUpload2Args["name"] = name
        fileUpload2Args["age"] = age
//这里并不会被当成文件，因为没有文件名(包含在Content-Disposition请求头中)，但上面的 filePart 有
//fileUpload2Args.put("file", file);
//这里并不会被当成文件，因为没有文件名(包含在Content-Disposition请求头中)，但上面的 filePart 有
//fileUpload2Args.put("file", file);
        val call4: Call<ResponseBody> = service.testFileUpload2(fileUpload2Args, filePart) //单独处理文件

        ResponseBodyPrinter.printResponseBody(call4)
    }
    companion object {
        const val TAG = "RetrofitHomeActivity"
    }
}