package com.example.cutsomadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView

// 뷰를 가지고 있는 역할
// 즉, 홀더 클래스 내의 각 항목을 구성하기 위한 뷰를 구성
// 어뎁터 쪽에서는 뷰를 따로 준비할 필요가 없이 holder내의 뷰를 사용

class DriveHolder(root : View) {

    var typeImageView : ImageView
    var titleView : TextView
    var dateView : TextView
    var menuImageView : ImageView

    init {
        typeImageView = root.findViewById(R.id.custom_item_type_image)
        titleView = root.findViewById(R.id.custom_item_title)
        dateView = root.findViewById(R.id.custom_item_date)
        menuImageView = root.findViewById(R.id.custom_item_menu)
    }

}