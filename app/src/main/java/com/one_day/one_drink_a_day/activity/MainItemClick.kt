package com.one_day.one_drink_a_day.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.one_day.one_drink_a_day.DoubleClickBackPressed
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.adapter.MainItemClickPagerAdapter
import com.one_day.one_drink_a_day.databinding.ActivityMainItemClickBinding
import com.one_day.one_drink_a_day.firebase.FirebaseRead

class MainItemClick : AppCompatActivity() {
    private lateinit var binding: ActivityMainItemClickBinding
    private lateinit var mainItemClickPagerAdapter: MainItemClickPagerAdapter
    private lateinit var doubleClickBackPressed : DoubleClickBackPressed
    private val TAG = "MainItemClick"
    companion object{
        var itemClickPosition = 0
        var alcoholCount : ArrayList<String> = arrayListOf()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainItemClickBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainItemClickPagerAdapter = MainItemClickPagerAdapter(this)
        doubleClickBackPressed = DoubleClickBackPressed(this)

        val parentIntent = intent
        itemClickPosition = parentIntent.getIntExtra("pos",0)
        alcoholCount.add(FirebaseRead.test[itemClickPosition]?.keys.toString())
        Log.d(TAG, "$itemClickPosition")
        Log.d(TAG, "카운트 $alcoholCount")



        binding.apply {

            mainItemViewpager.adapter = mainItemClickPagerAdapter
            mainItemViewpager.orientation  = ViewPager2.ORIENTATION_HORIZONTAL

            mainItemSpringDotsIndicator.setViewPager2(mainItemViewpager)

            mainItemViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.d(TAG,"리스너 안 $position")
                    if(position==0){
                        mainItemBtnBack.visibility = View.GONE
                    }
                    else if(position==3){
                        mainItemBtnNext.visibility = View.GONE
                    }
                    else{
                        mainItemBtnBack.visibility = View.VISIBLE
                        mainItemBtnNext.visibility = View.VISIBLE
                    }
                }
            })
            mainItemBtnNext.setOnClickListener {
                val currentNext = mainItemViewpager.currentItem
                mainItemViewpager.setCurrentItem(currentNext+1, true)
            }

            mainItemBtnBack.setOnClickListener {
                val currentBack = mainItemViewpager.currentItem
                mainItemViewpager.setCurrentItem(currentBack-1,true)
            }
        }
    }
    override fun onBackPressed() {
        if (binding.mainItemViewpager.currentItem == 0) {
            // 사용자가 첫 번째 페이지에서 뒤로가기 버튼을 누르면 finish 하도록 하고
            doubleClickBackPressed.backPressed(resources.getString(R.string.BackPressedMessage))
        } else {
            // 그렇지 않으면 이전 페이지로 이동하게 한다.
            binding.mainItemViewpager.currentItem = binding.mainItemViewpager.currentItem - 1
        }
    }
}