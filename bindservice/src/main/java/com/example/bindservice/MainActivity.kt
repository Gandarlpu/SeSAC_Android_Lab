package com.example.bindservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    lateinit var binder : MyService.MyBinder

    val connection : ServiceConnection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binder = service as MyService.MyBinder
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<ImageView>(R.id.startButton)
        val stopButton = findViewById<ImageView>(R.id.stopButton)

        startButton.setOnClickListener {
            binder.startMusic()
            startButton.setEnabled(false)
            stopButton.setEnabled(true)
        }

        stopButton.setOnClickListener {
            binder.stopMusic()
            startButton.setEnabled(true)
            stopButton.setEnabled(false)
        }
        val intent = Intent(this , MyService::class.java)
        bindService(intent , connection , Context.BIND_AUTO_CREATE)

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }
}