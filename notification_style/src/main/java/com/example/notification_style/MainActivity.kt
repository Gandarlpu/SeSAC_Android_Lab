package com.example.notification_style

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder : NotificationCompat.Builder
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channelId = "one"
                val channelName = "channel name"
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_HIGH
                )
                channel.description = "one description"
                manager.createNotificationChannel(channel)

                builder = NotificationCompat.Builder(this , channelId)
            }else{
                builder = NotificationCompat.Builder(this)
            }

            // 빌더 설정
            builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
            builder.setWhen(System.currentTimeMillis())
            builder.setContentText("Text")
            builder.setContentTitle("Title")

            // 알림 터치 시 화면 이동
            val actionIntent = Intent(this , DetailActivity::class.java)
            val actionPending = PendingIntent.getActivity(this , 20 , actionIntent , PendingIntent.FLAG_CANCEL_CURRENT)
            builder.setContentIntent(actionPending)

            // 액션 인텐트
            val actionIntent2 = Intent(this , DetailActivity::class.java)
            val actionPending2 = PendingIntent.getActivity(this , 20 , actionIntent , PendingIntent.FLAG_CANCEL_CURRENT)
            builder.addAction(
                NotificationCompat.Action.Builder(
                    android.R.drawable.stat_notify_more,
                    "Action",
                    actionPending2
                ).build()
            )

            // 스타일 추가
            val bigPicture = BitmapFactory.decodeResource(resources , R.drawable.logo_1)
            val style = NotificationCompat.BigPictureStyle()
            style.bigPicture(bigPicture)
            builder.setStyle(style)

            // Notification 발생
            manager.notify(1 , builder.build())

        }



    }
}