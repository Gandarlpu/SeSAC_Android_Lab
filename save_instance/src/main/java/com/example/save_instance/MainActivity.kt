package com.example.save_instance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var count = 0
    lateinit var editView : EditText
    lateinit var countView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        countView = findViewById(R.id.countView)
        val button = findViewById<Button>(R.id.button)
        editView = findViewById(R.id.edit)

        button.setOnClickListener {
            count++
            countView.text = "$count"
        }


        //edittext는 왜계소찍힘?
        //edittext에서 계속해서 짧게 캐싱함
        //내부기법에서 지원
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("count" , count)
        outState.putString("edit" , editView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.getInt("count")
        countView.setText("$count")
        editView.setText(savedInstanceState.getString("edit"))
    }
}