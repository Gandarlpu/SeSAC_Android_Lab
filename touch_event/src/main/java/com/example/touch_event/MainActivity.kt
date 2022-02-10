package com.example.touch_event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var resultView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        resultView = findViewById<TextView>(R.id.resultView)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        var eventType = ""
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                eventType = "Down Event"
            }
            MotionEvent.ACTION_UP -> {
                eventType = "Up Event"
            }
            MotionEvent.ACTION_MOVE -> {
                eventType = "Move Evnet"
            }
        }
        resultView.text = "$eventType : x - ${event?.x} , y - ${event?.y}"

        return super.onTouchEvent(event)
    }

}