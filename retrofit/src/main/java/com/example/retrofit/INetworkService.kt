package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface INetworkService {
    @GET("api/users")
    fun doGetUserList(@Query("page") page : String) : Call<UserListModel> // UserListModel에 결과값 전달
    // page : String = 어노테이션을 통헤(Query) 서버에 넘겨라
    // (@Query("page) = 키값 / page : String = value값으로 서버에 넘어감
    // doGetUserList함수를 호출했을 때 서버 연동을 위한 url정보 = @GET(baseURL/path)
}