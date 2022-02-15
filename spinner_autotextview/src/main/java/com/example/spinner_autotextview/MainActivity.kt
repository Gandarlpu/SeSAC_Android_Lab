package com.example.spinner_autotextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val spinner = findViewById<Spinner>(R.id.spinner)
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.auto)

        val datas = resources.getStringArray(R.array.language)
        val adapter : ArrayAdapter<String> = ArrayAdapter(
            this, R.layout.support_simple_spinner_dropdown_item , datas
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val autoDatas = arrayOf<String>("apply" , "apple" , "below")
        val autoAdapter = ArrayAdapter<String>(
            this, android.R.layout.simple_dropdown_item_1line,
            autoDatas
        )
        autoCompleteTextView.setAdapter(autoAdapter)

    }
}