package com.example.cutsomadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mutableList = mutableListOf<DriveVO>()
        mutableList.add(DriveVO("안드로이드" , "2월 5일" , "doc"))
        mutableList.add(DriveVO("DB.zip" , "2월 5일" , "file"))
        mutableList.add(DriveVO("이미지" , "2월 5일" , "image"))

        val listView = findViewById<ListView>(R.id.custom_listView)
        val adapter = DriveAdapter(this , R.layout.custom_item , mutableList)
        // ArrayAdapter의 매개변수로 처리 , 왜냐면 DriveAdapter가 ArrayAdapter를 상속받음.
        listView.adapter = adapter

    }
}