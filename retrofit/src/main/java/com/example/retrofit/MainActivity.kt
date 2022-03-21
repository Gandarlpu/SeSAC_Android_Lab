package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val retrofit : Retrofit
    get() = Retrofit.Builder()
        .baseUrl("httpsL//reqres.in/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)
        // 우리가 만든 interface를 Retrofit에 알려줘서 그 인터페이스 정보대로 네트워킹을 하기위한 객체 얻기
        var networkService = retrofit.create(INetworkService::class.java)

        // 위 인터페이스 객체의 함수를 콜 해줘야 하는데. 우리가 만든 함수
        val call = networkService.doGetUserList("1")

        // 네트워킹 발생
        call.enqueue(object : Callback<UserListModel>{
            override fun onResponse(call: Call<UserListModel>, response: Response<UserListModel>) {
                val userList = response.body()
                val mutableList = mutableListOf<Map<String , String>>()
                userList?.data?.forEach{
                    val map = mapOf("firstName" to it.firstName , "lastName" to it.lastName)
                    mutableList.add(map)
                }
                val adapter = SimpleAdapter(
                    this@MainActivity ,
                    mutableList ,
                    android.R.layout.simple_expandable_list_item_2,
                    arrayOf("firstName" , "lastName"),
                    intArrayOf(android.R.id.text1 , android.R.id.text2)
                )
                listView.adapter = adapter
            }

            override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                call.cancel()
            }

        })
    }
}