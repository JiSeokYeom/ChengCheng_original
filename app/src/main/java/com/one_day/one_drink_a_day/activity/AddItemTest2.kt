package com.one_day.one_drink_a_day.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.one_day.one_drink_a_day.DoubleClickBackPressed
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.adapter.AddItemPagerAdapter
import com.one_day.one_drink_a_day.databinding.ActivityAddItemTest2Binding


class AddItemTest2 : AppCompatActivity() {
    private lateinit var binding: ActivityAddItemTest2Binding
    private lateinit var addItemPagerAdapter: AddItemPagerAdapter
    private lateinit var doubleClickBackPressed : DoubleClickBackPressed
    private val TAG = "AddItemTest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemTest2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        addItemPagerAdapter = AddItemPagerAdapter(this)

        doubleClickBackPressed = DoubleClickBackPressed(this)
        binding.apply {
            addFmViewpager.adapter = addItemPagerAdapter
            addFmViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            springDotsIndicator.setViewPager2(addFmViewpager)
        }



    }

    override fun onBackPressed() {
        if (binding.addFmViewpager.currentItem == 0) {
            // 사용자가 첫 번째 페이지에서 뒤로가기 버튼을 누르면 finish 하도록 하고
            doubleClickBackPressed.backPressed(resources.getString(R.string.addItemBackPressedMessage))
        } else {
            // 그렇지 않으면 이전 페이지로 이동하게 한다.
            binding.addFmViewpager.currentItem = binding.addFmViewpager.currentItem - 1
        }
    }
}

