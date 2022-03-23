package com.example.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemMainBinding

// 뷰를 가지는 녀석
class MyViewHolder(val binding : ItemMainBinding) : RecyclerView.ViewHolder(binding.root) // 뷰홀더 상속 후 루트 객체 전달
// 뷰가 항목에 3개엿다라면 6줄 정도는 나와야함
// 뷰바인딩은 바인딩 객체만 유지하면 되니까 1줄로 가능

class MyAdapter(val datas : List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.itemData.text = datas[position]
    }

}