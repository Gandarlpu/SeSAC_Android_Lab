package com.example.simpleadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val listView = findViewById<ListView>(R.id.main_list)

        val datas : ArrayList<HashMap<String , String>> = ArrayList()

        var map : HashMap<String , String> = HashMap()
        map["name"] = "LG 트윈스"
        map["content"] = "서울, 잠실 야구장"
        datas.add(map)

        //map객체 새로만들기
        //map의 각 키값을 덮어쓰기
        map = HashMap()
        map["name"] = "두산 베어스"
        map["content"] = "서울 잠실 야구장"
        datas.add(map)

        map = HashMap()
        map["name"] = "KT 위너스"
        map["content"] = "수원"
        datas.add(map)

        val adapter = SimpleAdapter(
            this, datas, android.R.layout.simple_list_item_2,
            arrayOf("name" , "content"),
            intArrayOf(android.R.id.text1 , android.R.id.text2)
        )

        listView.adapter = adapter

    }
}