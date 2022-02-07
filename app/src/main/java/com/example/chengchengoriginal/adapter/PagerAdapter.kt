package com.example.chengchengoriginal.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chengchengoriginal.fragment.MainFragment_Friend
import com.example.chengchengoriginal.fragment.MainFragment_Personal

class PagerAdapter(fm: Fragment) : FragmentStateAdapter(fm){
    private val mainFragmentPersonal = MainFragment_Personal()
    private val mainFragmentFriend = MainFragment_Friend()
    val NUM_PAGES = 2
  /*  override fun getCount(): Int = NUM_PAGES
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> mainFragmentPersonal
            else -> mainFragmentFriend
        }
    }*/

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> mainFragmentPersonal
            else -> mainFragmentFriend
        }
    }

}