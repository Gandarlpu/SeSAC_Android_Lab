package com.example.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyAdapter(activity : FragmentActivity) : FragmentStateAdapter(activity)  {
    val fragments : List<Fragment>
    init{
        fragments = listOf(OneFragment() , Tworagment() , ThreeFragment())
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}