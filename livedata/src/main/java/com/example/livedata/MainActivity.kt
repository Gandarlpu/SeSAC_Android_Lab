package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val model : MyViewModel by viewModels()

        binding.button.setOnClickListener {
            model.callSum().observe(this){
                // 옵져버 등록
                binding.resultView.text = it
            }
        }
    }
}