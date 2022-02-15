package com.example.listciew_adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    val todos = mutableListOf<String>()
    lateinit var adapter: ArrayAdapter<String>
    lateinit var listView : ListView
    lateinit var editText: EditText
    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.main_list)
        editText = findViewById(R.id.edit)
        btn = findViewById(R.id.btn)

        adapter = ArrayAdapter(
            this , android.R.layout.simple_list_item_1,
            todos
        )

        listView.adapter = adapter

        // 삭제
        listView.setOnItemClickListener{ adapterView , view , i , l ->

            AlertDialog.Builder(this)
                .setTitle("remove todo")
                .setPositiveButton("OK"){ dialog , which ->
                    //제거
                    todos.removeAt(i)
                    adapter.notifyDataSetChanged()
                }
                .setNegativeButton("cancel" , null)
                .create()
                .show()

        }

        // 추가
        btn.setOnClickListener {

            todos.add(editText.text.toString())
            editText.text.clear()
            adapter.notifyDataSetChanged()

        }

    }
}