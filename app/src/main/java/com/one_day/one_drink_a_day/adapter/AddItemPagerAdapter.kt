package com.one_day.one_drink_a_day.adapter

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.one_day.one_drink_a_day.fragment.AddItemFragment1
import com.one_day.one_drink_a_day.fragment.AddItemFragment2
import com.one_day.one_drink_a_day.fragment.AddItemFragment3
import com.one_day.one_drink_a_day.fragment.AddItemFragment4

class AddItemPagerAdapter(fragment: FragmentActivity):FragmentStateAdapter(fragment) {
    private val NUM_PAGES = 4

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AddItemFragment1()
            1 -> AddItemFragment2()
            2 -> AddItemFragment3()
            else -> AddItemFragment4()
        }
    }
}