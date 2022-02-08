package com.example.chengchengoriginal.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.chengchengoriginal.adapter.PagerAdapter
import com.example.chengchengoriginal.databinding.FragmentMainPersonalBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment_Personal : Fragment() {
    private val TAG = "MainFragment_Personal"
    private var mbinding: FragmentMainPersonalBinding? = null
    private val binding get() = mbinding!!
    private lateinit var mainFragmentFriend: MainFragment_Friend
    private lateinit var myPageFragment: MypageFragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        mbinding = FragmentMainPersonalBinding.inflate(inflater,container,false)
       // val view = inflater.inflate(R.layout.fragment_main_personal, container, false)
        mainFragmentFriend = MainFragment_Friend()
        myPageFragment = MypageFragment()
        binding.viewpager.offscreenPageLimit = 2
      //  binding.viewpager.adapter = pagerAdapter
        binding.viewpager.currentItem = 1

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val pagerAdapter = PagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(myPageFragment,"개인")
        pagerAdapter.addFragment(mainFragmentFriend,"친구")

        binding.viewpager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewpager)

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewpager.currentItem = tab.position
                    pagerAdapter.notifyDataSetChanged()
                    Log.d(TAG,"if안임")
                    pagerAdapter.getItem(tab.position)
                    Log.d(TAG,"${pagerAdapter.getItem(tab.position)}")
                    Log.d(TAG,"${tab.position}")

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })


    }

    override fun onDestroy() {
        mbinding = null
        super.onDestroy()
    }

}




