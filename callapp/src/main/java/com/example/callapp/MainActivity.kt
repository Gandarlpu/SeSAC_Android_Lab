package com.example.callapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editView = findViewById<EditText>(R.id.editView)
        val button = findViewById<Button>(R.id.button)

        val permissionLauncher = registerForActivityResult(
            // 퍼미션 요청
            ActivityResultContracts.RequestPermission()
        ){ isGranted ->
            if(isGranted){
                // 퍼미션 잘 받아왔다면
                val intent = Intent(Intent.ACTION_CALL , Uri.parse("tel:${editView.text}"))
                // 발생
                startActivity(intent)
            }else{
                Toast.makeText(this , "denied" , Toast.LENGTH_SHORT).show()
            }
        }

        button.setOnClickListener {
            val status = ContextCompat.checkSelfPermission(this , "android.permission.CALL_PHONE")
            if(status == PackageManager.PERMISSION_GRANTED){
                //정상적으로 퍼미션 적용됫다면
                val intent = Intent(Intent.ACTION_CALL , Uri.parse("tel:${editView.text}"))
                // 발생
                startActivity(intent)
            }else{
                permissionLauncher.launch("android.permission.CALL_PHONE")
            }
        }

    }
}