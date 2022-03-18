package com.example.locationmanager

import android.Manifest
import android.app.Instrumentation
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat

class MainActivity : AppCompatActivity() {

    lateinit var resultView : TextView
    lateinit var manager : LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultView = findViewById(R.id.resultView)
        manager = getSystemService(LOCATION_SERVICE) as LocationManager

        // 퍼미션의 권한부여 여부
        val launcher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){ isGranted ->
            if(isGranted){
                getLocation()
            }else{
                Toast.makeText(this , "denied..." , Toast.LENGTH_SHORT).show()
            }
        }

        // 퍼미션 체크
        val status = ContextCompat.checkSelfPermission(this
            , "android.permission.ACCESS_FINE_LOCATION")
        if(status == PackageManager.PERMISSION_GRANTED){
            // 퍼미션 되어 있으면
            getLocation()
        }else{
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    fun getLocation(){
        val location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER) // 빨간 줄은 경고

        location?.let{
            // null이 아닐 때 location이
            val latitude = location.latitude
            val longitude = location.longitude
            val accuracy = location.accuracy
            val time = location.time

            resultView.text = "$latitude , $longitude , $accuracy , $time"
        }

    }

}