package com.example.cutsomadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.arrayadapter.R

class DriveAdapter(cxt : Context, val resId : Int, val datas : MutableList<DriveVO>)
    : ArrayAdapter<DriveVO>(cxt, resId) {

    override fun getCount(): Int {
        return datas.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        if(convertView == null){
            // 사용할 수 있는 뷰 객체가 준비가 안되있으면
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            // LayoutInfalter = xml파일로 정의되어 있는 레이아웃 리소스를 초기화시켜서
            // 뷰 객체를 생성하는 작업

            convertView = inflater.inflate(resId, null)
            // resId = custom_item.xml

            val holder = DriveHolder(convertView)
            // 홀더 클래스에 뷰를 받아와서 xml뷰객체를 사용
            // convertVIew는 위에서 resId를 가지고 item.xml을 가지고 있는 View객체임
            convertView!!.tag = holder
            // 이후에 쓰기위해 tag에 붙여넣음
        }

        val holder = convertView.getTag() as DriveHolder
        // 위에서 붙였던 tag를 getTag로 획득

        val typeImageView = holder.typeImageView
        val titleView = holder.titleView
        val dateView = holder.dateView
        val menuImageView = holder.menuImageView
        //holder에 있는 뷰를 획득

        val (title, date, type) = datas[position]
        titleView.text = title
        dateView.text = date

        if(type == "doc"){
            typeImageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.ic_type_doc, null
                )
            )
        }else if(type == "file"){
            typeImageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.ic_type_file, null
                )
            )
        }else if(type == "img"){
            typeImageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.ic_type_image, null
                )
            )
        }
        menuImageView.setOnClickListener {
            Toast.makeText(context , "$title menu click" , Toast.LENGTH_SHORT).show()
        }

        return convertView
    }

}