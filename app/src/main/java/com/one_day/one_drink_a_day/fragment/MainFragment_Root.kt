package com.one_day.one_drink_a_day.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.one_day.one_drink_a_day.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.one_day.one_drink_a_day.databinding.FragmentMainRootBinding


class MainFragment_Root : Fragment() {
    private val TAG = "MainFragment_Root"
    private var mbinding: FragmentMainRootBinding? = null
    private val binding get() = mbinding!!
    private lateinit var mainFragmentFriend: MainFragment_Friend
    private lateinit var mainFragmentPersonal: MainFragment_Personal
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        mbinding = FragmentMainRootBinding.inflate(inflater,container,false)
        mainFragmentFriend = MainFragment_Friend()
        mainFragmentPersonal = MainFragment_Personal()
        binding.viewpager.offscreenPageLimit = 2
        binding.viewpager.currentItem = 1

        val pagerAdapter = PagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(mainFragmentPersonal,"개인")
        pagerAdapter.addFragment(mainFragmentFriend,"친구")

        binding.viewpager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewpager)

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    pagerAdapter.getItem(tab.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        return binding.root

    }

    override fun onDestroy() {
        mbinding = null
        super.onDestroy()
    }

}




