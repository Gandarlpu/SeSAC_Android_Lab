package com.example.keyevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var initTime = 0L
    // 3초 이내에 백 버튼이 2번 눌리면 메인 엑티비티를 벗어나기 위해

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // KeyCode_Back 즉, 뒤로가기 버튼을 한번 누르면 종료되야 하는 것을
        // initTime으로 현재 뒤로가기 버튼을 누른 시간이 3초가 안됫을 떄 종료(뒤로가기)하는 것
        if(keyCode === KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - initTime > 3000){
                Toast.makeText(this , "종료하려면 한 번 더 누르세요." , Toast.LENGTH_SHORT).show()
                return true
                initTime = System.currentTimeMillis()
            }
        }

        return super.onKeyDown(keyCode, event)
    }


}






