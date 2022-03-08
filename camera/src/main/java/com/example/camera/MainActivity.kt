package com.example.camera

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val dataButton = findViewById<Button>(R.id.dataButton)
        val fileButton = findViewById<Button>(R.id.fileButton)

        val launcher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            // 이미지 받아와서 화면에 찍기
            val bitmap = it.data?.getExtras()?.get("data") as Bitmap
            bitmap?.let{
                imageView.setImageBitmap(bitmap)
            }
        }
        // 카메라앱 연동
        dataButton.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            launcher.launch(intent)
        }
        var filePath = ""
        val fileLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            // 파일 정보 받아옴
            val option = BitmapFactory.Options()
            option.inSampleSize = 3
            val bitmap = BitmapFactory.decodeFile(filePath , option)
            bitmap?.let{
                imageView.setImageBitmap(bitmap)
            }
        }

        fileButton.setOnClickListener {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            filePath = file.absolutePath
            val uri = FileProvider.getUriForFile(
                this,
                "com.example.camera.files.Pictures",
                file
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT , uri)
            fileLauncher.launch(intent)
        }

    }
}