package com.example.chengchengoriginal.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.chengchengoriginal.DoubleClickBackPressed
import com.example.chengchengoriginal.R
import com.example.chengchengoriginal.databinding.ActivityMainBinding
import com.example.chengchengoriginal.fragment.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var doubleClickBackPressed : DoubleClickBackPressed
    private val mainFragmentRoot = MainFragment_Root()
    private val myPageFragment = MypageFragment()
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG,"실행")
        doubleClickBackPressed = DoubleClickBackPressed(this)
        initNavigationBar()


    }

    private fun initNavigationBar(){
        val addItem = Intent(this,AddItem::class.java)
        binding.bottomNavi.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_box -> {
                    changeFragment(mainFragmentRoot)
                 }
                 R.id.add_box -> {
                      startActivity(addItem)
                    // binding.bottomNavi.visibility = View.GONE
                    // changeFragment(addItemFragment)
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
            .commit()
    }

    override fun onBackPressed() {
        binding.bottomNavi.menu.findItem(R.id.home_box).isChecked = true
        changeFragment(mainFragmentRoot)
        doubleClickBackPressed.backPressed(resources.getString(R.string.mainBackPressedMessage))
    }

    override fun onResume() {
        super.onResume()
        binding.bottomNavi.menu.findItem(R.id.home_box).isChecked = true
        changeFragment(mainFragmentRoot)
    }
}

