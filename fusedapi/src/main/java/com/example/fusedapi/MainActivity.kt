package com.example.fusedapi

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    lateinit var resultView : TextView
    lateinit var providerClient : FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultView = findViewById(R.id.resultView)

        val apiClient = GoogleApiClient.Builder(this)
            .addApi(LocationServices.API)
            .addConnectionCallbacks(connectionCallback)
            .addOnConnectionFailedListener(connectionFailedCallback)
            .build()

        providerClient = LocationServices.getFusedLocationProviderClient(this)

        val launcher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){
            if(it){
                apiClient.connect()
            }else{
                Toast.makeText(this , "denied..." , Toast.LENGTH_SHORT).show()
            }
        }

        val status = ContextCompat.checkSelfPermission(this , "android.permission.ACCESS_FINE_LOCATION")
        if(status == PackageManager.PERMISSION_GRANTED){
            apiClient.connect()
        }else{
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

    }

    val connectionCallback = object : GoogleApiClient.ConnectionCallbacks{
        override fun onConnected(p0: Bundle?) {
            // 프로바이더 사용 상태가 가능할 때 호출
            providerClient.lastLocation.addOnSuccessListener {
            //퍼미션 체크셀크로 처리해줘야되서 빨간 줄 우리는 다른 곳에서 처리
                val latitude = it?.latitude
                val longitude = it?.longitude
                resultView.text = "$latitude , $longitude"
            }
        }

        override fun onConnectionSuspended(p0: Int) {
            // 이용하던 프로바이더가 이용 불가능한것을 알려주기 위해 호출
        }
    }

    val connectionFailedCallback = object : GoogleApiClient.OnConnectionFailedListener {
        override fun onConnectionFailed(p0: ConnectionResult) {
            // 콜벡함수 실패 시 호출되는 함수
        }
    }

}