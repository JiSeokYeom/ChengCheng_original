package com.one_day.one_drink_a_day.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.one_day.one_drink_a_day.DoubleClickBackPressed
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.adapter.AddItemPagerAdapter
import com.one_day.one_drink_a_day.databinding.ActivityAddItemTest2Binding


class AddItem : AppCompatActivity() {
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

            addFmViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.d(TAG,"리스너 안 $position")
                    if(position==0){
                        btnBack.visibility = View.GONE
                    }
                    else{
                        btnBack.visibility = View.VISIBLE
                    }
                }
            })
            btnNext.setOnClickListener {
                val currentNext = addFmViewpager.currentItem
                addFmViewpager.setCurrentItem(currentNext+1, true)
            }

            btnBack.setOnClickListener {
                val currentBack = addFmViewpager.currentItem
                addFmViewpager.setCurrentItem(currentBack-1,true)
            }


        }



    }

    override fun onBackPressed() {
        if (binding.addFmViewpager.currentItem == 0) {
            // 사용자가 첫 번째 페이지에서 뒤로가기 버튼을 누르면 finish 하도록 하고
            doubleClickBackPressed.backPressed(resources.getString(R.string.BackPressedMessage))
        } else {
            // 그렇지 않으면 이전 페이지로 이동하게 한다.
            binding.addFmViewpager.currentItem = binding.addFmViewpager.currentItem - 1
        }
    }
}

