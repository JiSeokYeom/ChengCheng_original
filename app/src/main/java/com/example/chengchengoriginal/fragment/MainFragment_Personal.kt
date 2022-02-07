package com.example.chengchengoriginal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chengchengoriginal.adapter.PagerAdapter
import com.example.chengchengoriginal.databinding.FragmentMainPersonalBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment_Personal : Fragment() {
    private var mbinding: FragmentMainPersonalBinding? = null
    private val binding get() = mbinding!!
    private lateinit var mainFragmentFriend: MainFragment_Friend
    private lateinit var tabLayoutMediator: TabLayoutMediator
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        mbinding = FragmentMainPersonalBinding.inflate(inflater,container,false)
       // val view = inflater.inflate(R.layout.fragment_main_personal, container, false)
        mainFragmentFriend = MainFragment_Friend()
        binding.viewpager.adapter = PagerAdapter(this)
        binding.viewpager.currentItem = 0
      //  viewPager2.setAdapter(new viewPagerAdapter(this));
    /*    tabLayoutMediator = TabLayoutMediator(binding.tabLayout,binding.viewpager, object :
            TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                if(position == 0) {
                    tab.text = "개인";
                } else {
                    // 두번째 화면일 때
                    tab.text = "친구";
                }
            }
        })
         tabLayoutMediator.attach()*/


        return binding.root
        //return view
    }

    override fun onDestroy() {
        mbinding = null
        super.onDestroy()
    }

}




