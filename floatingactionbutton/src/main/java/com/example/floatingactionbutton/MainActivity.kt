package com.example.floatingactionbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.floatingactionbutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener{
            when(binding.fab.isExtended){// 확장되고잇는지 아닌지
                true -> binding.fab.shrink() // 줄어들어라
                false -> binding.fab.extend() // 늘어나라
            }
        }

    }
}