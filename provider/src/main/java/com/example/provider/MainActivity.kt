package com.example.provider

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val resultView = findViewById<TextView>(R.id.resultView)

        // 주소록에서 되돌아왔을 떄
        val requestActivity : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            val cursor = contentResolver.query(
                it.data!!.data!!,
                //사람 이름과 전화번호
                arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    ),
                null,
                null,
                null
            )
            var name = "none"
            var phone = "none"
            if(cursor!!.moveToFirst()){
                name = cursor?.getString(0)
                phone = cursor?.getString(1)
            }
            resultView.text = "name - $name , phone - $phone"

        }

        // 퍼미션 발생
        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){ isGranted ->
            if(isGranted){
                val intent = Intent(Intent.ACTION_PICK , ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                requestActivity.launch(intent)
            }
        }
        
        button.setOnClickListener { 
            val status = ContextCompat.checkSelfPermission(this , "android.permission.READ_CONTACTS")
            if(status == PackageManager.PERMISSION_GRANTED){
                // 퍼미션이 있다면
                val intent = Intent(Intent.ACTION_PICK , ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                requestActivity.launch(intent)
            }else{
                //퍼미션이 없다면
                permissionLauncher.launch("android.permission.READ_CONTACTS")
            }
        }
    }
}