package com.zhewen.retrofitlab.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Query
import retrofit2.http.Url


interface ApiService {
    @GET("api/rand.music")
    fun getMusicData(@Query("sort") sort:String,@Query("format") format:String): Call<MusicData?>?

    @GET
    fun getDynamicUrl(@Url url: String?): Call<ResponseBody?>?

    @GET("api/dynamicHeadersInfo")
    fun getDynamicHeadersInfo(@HeaderMap headers: Map<String?, String?>?): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST("api/fieldParam")
    fun postFieldFun(@Field("key") key: String?): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST("api/fieldMapParam")
    fun postFieldMapFun(@FieldMap params: Map<String?, String?>?): Call<ResponseBody?>?

    @POST("api/bodyParam")
    fun postBodyFun(@Body postBodyBean: PostBodyBean?): Call<ResponseBody?>?

    @POST("/form")
    @Multipart
    fun testFileUpload1(
        @Part("name") name: RequestBody?,
        @Part("age") age: RequestBody?,
        @Part file: MultipartBody.Part?
    ): Call<ResponseBody?>?

    @POST("/form")
    @Multipart
    fun testFileUpload2(@PartMap args: Map<String?, RequestBody?>?, @Part file: MultipartBody.Part?): Call<ResponseBody?>?

    @POST("/form")
    @Multipart
    fun testFileUpload3(@PartMap args: Map<String?, RequestBody?>?): Call<ResponseBody?>?


    @GET("api/rand.music")
    suspend fun getTranslation(@Query("sort") sort:String,@Query("format") format:String):MusicData
}