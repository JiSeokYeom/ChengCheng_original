package com.one_day.one_drink_a_day.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.one_day.one_drink_a_day.*
import com.one_day.one_drink_a_day.adapter.AddItemPagerAdapter
import com.one_day.one_drink_a_day.databinding.ActivityAddItemBinding
import com.one_day.one_drink_a_day.databinding.ActivityTitleInputBinding


class TitleInput : AppCompatActivity() {
    private lateinit var binding: ActivityTitleInputBinding
    private val TAG = "TitleInput"
    private lateinit var permission : Permission
    private lateinit var cropLibrary: CropLibrary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTitleInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

         val addItem = Intent(this,AddItem::class.java)
        permission = Permission(this)
        cropLibrary = CropLibrary(this,)

        binding.apply {

            btnTitleNext.setOnClickListener {
                startActivity(addItem)
                finish()
            }

        }
    }
}

