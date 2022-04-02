package com.zhewen.retrofitlab.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    fun getCall(): Call<Translation?>?

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    suspend fun getTranslation():Translation
}