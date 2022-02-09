package com.example.c11

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val name = TextView(this).apply{
            typeface = Typeface.DEFAULT_BOLD
            text = "SeSAC"
        }


        val image = ImageView(this).apply {
            this.setImageDrawable(ContextCompat.getDrawable(
                this@MainActivity, R.drawable.ic_launcher_background
            ))
        }

        val title = TextView(this).apply {
            typeface = Typeface.DEFAULT_BOLD
            text = "JectPack 과 Kotlin을 활용한 Android App 개발"
        }

        // ViewGroup으로 위의 구성들을 하나로 묶어줌
        val layout = LinearLayout(this).apply{
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            addView(name , WRAP_CONTENT , WRAP_CONTENT)
            addView(image , WRAP_CONTENT , WRAP_CONTENT)
            addView(title , WRAP_CONTENT , WRAP_CONTENT)
        }


        setContentView(layout)




    }
}