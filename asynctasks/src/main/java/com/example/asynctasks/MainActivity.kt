package com.example.asynctasks

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var startView : ImageView
    lateinit var pauseView : ImageView
    lateinit var textView: TextView

    var isFirst = true

    lateinit var asyncTask : MyAsyncTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startView = findViewById(R.id.main_startBtn)
        pauseView = findViewById(R.id.main_pauseBtn)
        textView = findViewById(R.id.main_textView)

        startView.setOnClickListener {
            if(isFirst){
                asyncTask.isRun = true
                asyncTask.execute() // execute함수가 실행시키는 부분
                isFirst = false
            }else{
                asyncTask.isRun = true
            }
        }

        pauseView.setOnClickListener {
            asyncTask.isRun = false
        }
        asyncTask = MyAsyncTask() // MyAsyncTask객체 생성
    }

    inner class MyAsyncTask : AsyncTask<Void? , Int? , String>(){
        var loopFlag = true
        var isRun = false
        override fun doInBackground(vararg params: Void?): String {
            var count = 10
            while(loopFlag){
                SystemClock.sleep(1000)
                if(isRun){
                    count --
                    //뷰에접근불가, 데이터만 전달
                    publishProgress(count)
                    if(count == 0){
                        loopFlag = false
                    }
                }
            }
            return "Finish!!"
        }

        // publishProgess호출 시 여기에서 UI를 업데이트
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            textView.setText(values[0].toString())
        }
        // doInBackGround가 끝날 때 마지막 return 값을 받아서 UI처리
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            textView.setText(result)
        }
    }
}