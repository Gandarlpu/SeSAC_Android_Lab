package com.example.livedata

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class MyViewModel : ViewModel() {

    var sum = 0
    fun callSum() : MutableLiveData<String> {
        val liveData = MutableLiveData<String>()
        thread {
            for(i in 1..10){
                sum += i
                liveData.postValue(sum.toString()) // 이 순간 액티비티의 옵져버에 전달(10번 전달)
                SystemClock.sleep(100)
            }
        }
        return liveData
    }

}