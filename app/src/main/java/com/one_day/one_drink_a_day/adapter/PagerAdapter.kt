package com.one_day.one_drink_a_day.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
    private var titleList : MutableList<String> = arrayListOf()
    private var fragmentList : MutableList<Fragment> = arrayListOf()
    val NUM_PAGES = 2
    override fun getCount(): Int = NUM_PAGES
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }


    fun addFragment(fragment: Fragment, tile: String){
        fragmentList.add(fragment)
        titleList.add(tile)
    }

}