package com.example.googlemap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

class MainActivity : AppCompatActivity() , OnMapReadyCallback{

    var googleMap : GoogleMap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment)!!.getMapAsync(this)


    }

    override fun onMapReady(p0: GoogleMap?) {
        // 지도 객체를 이용이 가능하다
        googleMap = p0
        val latLng = LatLng(37.566610 , 126.978403)
        val position = CameraPosition.Builder() // 지도화면
            .target(latLng)
            .zoom(16f)
            .build()
        // 위에서 설정한 정보대로 지도에 뿌려주기
        googleMap?.moveCamera(CameraUpdateFactory.newCameraPosition(position))

    }
}