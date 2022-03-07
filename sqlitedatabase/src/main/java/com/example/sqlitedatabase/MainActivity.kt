package com.example.sqlitedatabase

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {

            val db : SQLiteDatabase = DBHelper(this).readableDatabase

            val cursor = db.rawQuery("select name from tb_member" , null)
            if(cursor.moveToFirst()){
                Toast.makeText(this , "${cursor.getString(0)}" , Toast.LENGTH_SHORT).show()
            }
            db.close()
        }

    }
}