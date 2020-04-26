package com.baiheng.retrofitstudy;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetRequest_Interface {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
//    Call<Translation> getUserInfo(@Query("id") String userId, @Query("status") String Status);
    Call<Translation> getCall();
}
