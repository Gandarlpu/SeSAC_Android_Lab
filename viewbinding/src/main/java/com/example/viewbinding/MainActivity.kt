package com.example.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import com.example.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.visibleBtn.setOnClickListener {
            binding.targetView.visibility = View.VISIBLE
        }
        binding.invisibleBtn.setOnClickListener {
            binding.targetView.visibility = View.INVISIBLE
        }

    }
}