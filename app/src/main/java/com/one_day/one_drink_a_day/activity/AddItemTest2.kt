package com.one_day.one_drink_a_day.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import com.one_day.one_drink_a_day.adapter.AddItemPagerAdapter
import com.one_day.one_drink_a_day.databinding.ActivityAddItemTest2Binding


class AddItemTest2 : AppCompatActivity() {
    private lateinit var binding: ActivityAddItemTest2Binding
    private lateinit var addItemPagerAdapter: AddItemPagerAdapter
    private val TAG = "AddItemTest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemTest2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        addItemPagerAdapter = AddItemPagerAdapter(this)

        binding.apply {
            addFmViewpager.adapter = addItemPagerAdapter
        }



    }

    override fun onBackPressed() {
        //contentsInputTextCheck()
    }
}

