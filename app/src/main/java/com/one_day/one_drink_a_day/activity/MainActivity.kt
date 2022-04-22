package com.one_day.one_drink_a_day.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.one_day.one_drink_a_day.firebase.FirebaseDB
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.ActivityMainBinding
import com.one_day.one_drink_a_day.dialog.AlcoholCheckDialog
import com.one_day.one_drink_a_day.fragment.MainFragment
import com.one_day.one_drink_a_day.fragment.MypageFragment
import com.one_day.one_drink_a_day.model.MainRecyclerViewItem

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainFragment = MainFragment()
    private val myPageFragment = MypageFragment()
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG,"실행")
        initNavigationBar()

    }

    private fun initNavigationBar(){
        // val addItem = Intent(this,AddItem::class.java)
        val titleInput = Intent(this,TitleInput::class.java)
        binding.bottomNavi.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_box -> {
                    changeFragment(mainFragment)
                 }
                 R.id.add_box -> {
                     startActivity(titleInput)
                    // startActivity(addItem)
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


    override fun onResume() {
        super.onResume()
        binding.bottomNavi.menu.findItem(R.id.home_box).isChecked = true
        changeFragment(mainFragment)
    }

  /*  private fun itemListGet(){
        itemArray.clear()
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot : DataSnapshot in snapshot.children){
                    itemArray.add(MainRecyclerViewItem(dataSnapshot.child("TitleImg").value.toString(),dataSnapshot.child("TitleName").value.toString()))
                }
                Log.d(TAG,"아이템 어레이 확인 $itemArray")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }*/
}

