package com.one_day.one_drink_a_day.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.one_day.one_drink_a_day.DoubleClickBackPressed
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.ActivityMainBinding
import com.one_day.one_drink_a_day.dialog.AlcoholCheckDialog
import com.one_day.one_drink_a_day.fragment.MainFragment_Root
import com.one_day.one_drink_a_day.fragment.MypageFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var doubleClickBackPressed : DoubleClickBackPressed
    private val mainFragmentRoot = MainFragment_Root()
    private val myPageFragment = MypageFragment()
    private val checkDialog = AlcoholCheckDialog()
    private val TAG = "MainActivity"
    companion object{
        var checkSW = true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG,"실행")
        doubleClickBackPressed = DoubleClickBackPressed(this)
        initNavigationBar()
        if (checkSW){
            checkDialog.show(supportFragmentManager,"AlcoholCheckDialog")
            checkSW = false
        }


    }

    private fun initNavigationBar(){
        val addItem = Intent(this, AddItem::class.java)
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

