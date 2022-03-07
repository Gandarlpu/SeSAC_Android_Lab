package com.example.query

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class ReadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        val titleView = findViewById<TextView>(R.id.read_title)
        val contentView = findViewById<TextView>(R.id.read_content)

        val db = DBHelper(this).readableDatabase
        // query의 매개변수
        // (테이블 명, 배열형식의 컬럼명, where뒤에 들어갈 문자열, where조건에 들어갈 데이터, GroupBy절, Having절, orderBy절)
        val cursor = db.query("tb_memo" , arrayOf("title" , "content") ,
            null , null , null , null , "_id desc limit 1")

        while(cursor.moveToNext()){
            titleView.setText(cursor.getString(0))
            contentView.setText(cursor.getString(1))
        }
        db.close()
    }
}