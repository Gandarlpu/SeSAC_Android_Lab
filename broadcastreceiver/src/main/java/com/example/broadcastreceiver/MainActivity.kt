package com.example.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultView = findViewById<TextView>(R.id.resultView)
        val button = findViewById<Button>(R.id.button)

        // 브로드캐스트리시버 등록
        registerReceiver(null , IntentFilter(Intent.ACTION_BATTERY_CHANGED))!!.apply {
            // 리시버를 주진 않았지만, 인텐트 필더 정보를 이용해 배터리 정보를 받아올 수 있음
            var isCharging = "Not Plugged"

            when(getIntExtra(BatteryManager.EXTRA_STATUS , -1)){
                BatteryManager.BATTERY_STATUS_CHARGING -> {
                    // 현재 핸드폰이 충전되어 있는 상태
                    // 고속충전인지 저속충전인지 판단 가능
                    when(getIntExtra(BatteryManager.EXTRA_PLUGGED , -1)){
                        BatteryManager.BATTERY_PLUGGED_USB -> {
                            // 저속충전
                            isCharging = "USB Plugged"
                        }
                        BatteryManager.BATTERY_PLUGGED_AC -> {
                            // 고속충전
                            isCharging = "AC Plugged"
                        }
                    }
                }
            }

            val level = getIntExtra(BatteryManager.EXTRA_LEVEL , -1)
            val scale = getIntExtra(BatteryManager.EXTRA_SCALE , -1)
            val betteryPct = level / scale.toFloat() * 100

            resultView.text = "$isCharging , $betteryPct %"
        }

        button.setOnClickListener {
            val intent = Intent(this , MyReceiver::class.java)
            sendBroadcast(intent)
        }

    }
}