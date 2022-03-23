package com.one_day.one_drink_a_day.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.one_day.one_drink_a_day.DoubleClickBackPressed
import com.one_day.one_drink_a_day.firebase.FirebaseDB
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.ActivityMainBinding
import com.one_day.one_drink_a_day.dialog.AlcoholCheckDialog
import com.one_day.one_drink_a_day.fragment.MainFragment
import com.one_day.one_drink_a_day.fragment.MypageFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var doubleClickBackPressed : DoubleClickBackPressed
    private val mainFragmentPersonal = MainFragment()
    private val myPageFragment = MypageFragment()
    private val checkDialog = AlcoholCheckDialog()
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG,"실행")
        doubleClickBackPressed = DoubleClickBackPressed(this)
        initNavigationBar()

        // SharedPreferences를 사용해 한번만 실행 하기기
        // 첫 로그인 성공 후 메인으로 넘어 올 시 주량체크 (한번만 실행 됨)
        val pref = getSharedPreferences("checkFirst",MODE_PRIVATE)
        val checkFirst : Boolean = pref.getBoolean("checkFirst",false)
        if(!checkFirst){
            val editor : SharedPreferences.Editor = pref.edit()
            editor.putBoolean("checkFirst",true)

            editor.apply()
            FirebaseDB.userAdd()
            checkDialog.show(supportFragmentManager, "AlcoholCheckDialog")
        }

    }

    private fun initNavigationBar(){
        val addItem = Intent(this,AddItem::class.java)
        binding.bottomNavi.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_box -> {
                    changeFragment(mainFragmentPersonal)
                 }
                 R.id.add_box -> {
                   //  startActivity(addItemTest)
                     startActivity(addItem)
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
        changeFragment(mainFragmentPersonal)
        doubleClickBackPressed.backPressed(resources.getString(R.string.BackPressedMessage))
    }

    override fun onResume() {
        super.onResume()
        binding.bottomNavi.menu.findItem(R.id.home_box).isChecked = true
        changeFragment(mainFragmentPersonal)
    }
}

