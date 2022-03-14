package com.example.service

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.IBinder
import java.lang.Exception

class MyService : Service() {
    lateinit var player : MediaPlayer

    //서비스 내의 동적 등록방법
    var receiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            // 리시버는 액티비티에서 실행한다 = 인텐트 엑스트라 데이터로 데이터를 넘겼다.
            val mode = intent?.getStringExtra("mode")
            //mode의 intent가 start or stop이냐
            if(mode != null){
                if(mode == "start"){
                    try{
                        if(player!=null && player.isPlaying){
                            player.stop()
                            player.release()
                        }
                        player = MediaPlayer.create(context , R.raw.music)
                        player.start()
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }else if(mode == "stop"){
                    if(player != null && player.isPlaying){
                        player.stop()
                    }
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer()
        registerReceiver(receiver , IntentFilter("PLAY_TO_SERVICE"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO()
    }
}