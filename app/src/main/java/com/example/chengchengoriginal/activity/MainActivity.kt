package com.example.chengchengoriginal.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.chengchengoriginal.R
import com.example.chengchengoriginal.databinding.ActivityMainBinding
import com.example.chengchengoriginal.fragment.AddItemFragment
import com.example.chengchengoriginal.fragment.FriendFragment
import com.example.chengchengoriginal.fragment.MainFragment_Personal
import com.example.chengchengoriginal.fragment.MypageFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private val fT : FragmentTransaction = supportFragmentManager.beginTransaction()
    private val mainFragmentPersonal = MainFragment_Personal()
    private val addItemFragment = AddItemFragment()
    private val friendFragment = FriendFragment()
    private val myPageFragment = MypageFragment()
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG,"실행")
        initNavigationBar()


    }

    private fun initNavigationBar(){
        binding.bottomNavi.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_box -> {
                    changeFragment(mainFragmentPersonal)
                 }
                 R.id.add_box -> {
                     changeFragment(addItemFragment)
                 }
                 R.id.people_box -> {
                     changeFragment(friendFragment)
                 }
                 R.id.my_page_box -> {
                     changeFragment(myPageFragment)
                 }
            }
            true
        }
        binding.bottomNavi.selectedItemId = R.id.home_box
    }
    private fun changeFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
       //     .addToBackStack(null)
            .commit()
    }
}

