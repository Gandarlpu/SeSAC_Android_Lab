package com.example.jobscheduler

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : JobService() {

    override fun onCreate() {
        super.onCreate()
        Log.d("kkang" , "MyService...onCreate...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("kkang" , "MyService...onDestroy...")
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d("kkang" , "MyService...onStartJob...")
        return false // false = 완벽하게 작업 마무리
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d("kkang" , "MyService...onStopJob...")
        return false // false = 완벽하게 작업 마무리
    }
}