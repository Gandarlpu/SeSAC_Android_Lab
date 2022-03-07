package com.example.rawquert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titleView = findViewById<EditText>(R.id.add_title)
        val contentView = findViewById<EditText>(R.id.add_content)
        val addBtn = findViewById<Button>(R.id.add_btn)

        addBtn.setOnClickListener {
            val title = titleView.text.toString()
            val content = contentView.text.toString()

            val helper = DBHelper(this)
            val db = helper.writableDatabase
            // (?.?)는 배열 형식으로 뒤에서 초기화 해줘야함.
            db.execSQL("insert into tb_memo (title , content) values(?,?)",
                    arrayOf(title , content))
            db.close()

            val intent = Intent(this , ReadActivity::class.java)
            startActivity(intent)
        }

    }
}