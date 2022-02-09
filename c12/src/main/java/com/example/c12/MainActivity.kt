package com.example.c12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView


class MainActivity : AppCompatActivity() {


    lateinit var visibleButton : Button
    lateinit var invisibleButton : Button
    lateinit var logoImageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        visibleButton = findViewById(R.id.visible_btn)
        invisibleButton = findViewById(R.id.invisible_btn)
        logoImageView = findViewById(R.id.logo_ImageView)


    }
}