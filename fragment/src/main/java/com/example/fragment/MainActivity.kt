package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 프레그먼트 선언
        val oneFragment = OneFragment()
        val twoFragment = TwoFragment()

        // 프레그먼트 매니저 선언 / 트랜잭션 선언
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        transaction.add(R.id.fragment_content , oneFragment)
        transaction.commit()

        binding.oneButton.setOnClickListener {
            // commit 하면 트렌젝션은 닫힘
            // 따라서 다시 제어하려면 다시 선언
            val tran = manager.beginTransaction()
            tran.replace(R.id.fragment_content , oneFragment)
            tran.commit()
        }

        binding.twoButton.setOnClickListener {
            val tran = manager.beginTransaction()
            tran.replace(R.id.fragment_content , twoFragment)
            tran.commit()
        }

    }
}