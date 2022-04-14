package com.one_day.one_drink_a_day.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.one_day.one_drink_a_day.fragment.MainItemClickFragment1
import com.one_day.one_drink_a_day.fragment.MainItemClickFragment2
import com.one_day.one_drink_a_day.fragment.MainItemClickFragment3
import com.one_day.one_drink_a_day.fragment.MainItemClickFragment4

class MainItemClickPagerAdapter(fragment: FragmentActivity):FragmentStateAdapter(fragment) {
    private val NUM_PAGES = 4

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MainItemClickFragment1()
            1 -> MainItemClickFragment2()
            2 -> MainItemClickFragment3()
            else -> MainItemClickFragment4()
        }
    }
}