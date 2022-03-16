package com.example.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button);
        button.setOnClickListener {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder : NotificationCompat.Builder

            //Builder마다 버전이 다르기 때문에 분기별로 나눠줌
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channelId = "one-channel"
                val channelName = "My One Channel"
                //Notification 채널설정
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_HIGH
                )

                //Notification 추가기능 설정
                //Notification 설명
                channel.description = "My Channel One Description"
                //Badge아이콘에 Notification정보가 출력되게끔 할꺼냐 말꺼냐
                channel.setShowBadge(true)
                // 이 채널에 포함된 Notification 음을 플레이 하기 위한 준비(음원관련 정보)
                val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audio = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                channel.setSound(uri , audio) // 알람 음악 설정
                channel.enableLights(true) //빛 설정
                channel.lightColor = Color.RED // 빛 색깔
                channel.enableVibration(true) // 알람 진동 설정
                channel.vibrationPattern = longArrayOf(100,200,100,200)

                // Notification을 manager에 등록
                manager.createNotificationChannel(channel)

                // 채널 준비 끝 / 빌더 만들기
                builder = NotificationCompat.Builder(this , channelId)
            }else{
                // API26이하 버전
                builder = NotificationCompat.Builder(this)
            }

            // Builder에게 Notification이 어떻게 구성되라고 설정
            builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
            builder.setWhen(System.currentTimeMillis())
            builder.setContentTitle("Title")
            builder.setContentText("message")

            // 알람 띄위기
            manager.notify(1 , builder.build())

        }

    }
}