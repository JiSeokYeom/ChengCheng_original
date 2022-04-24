package com.one_day.one_drink_a_day.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.one_day.one_drink_a_day.DoubleClickBackPressed
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.adapter.MainItemClickPagerAdapter
import com.one_day.one_drink_a_day.databinding.ActivityMainItemClickBinding
import com.one_day.one_drink_a_day.firebase.FirebaseRead
import com.one_day.one_drink_a_day.viewmodel.MainItemClickViewModel
import com.one_day.one_drink_a_day.viewmodel.MainViewModel

class MainItemClick : AppCompatActivity() {
    private val mainItemClickViewModel: MainItemClickViewModel by lazy {
        ViewModelProvider(this).get(MainItemClickViewModel::class.java)
    }
    private lateinit var binding: ActivityMainItemClickBinding
    private lateinit var mainItemClickPagerAdapter: MainItemClickPagerAdapter
    private lateinit var doubleClickBackPressed: DoubleClickBackPressed
    private val TAG = "MainItemClick"
    private var cnt = 0
  //  private var alcoholCountKeyHash: HashMap<String, String> = hashMapOf()

    companion object {
        var itemClickPosition = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainItemClickBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainItemClickPagerAdapter = MainItemClickPagerAdapter(this)
        doubleClickBackPressed = DoubleClickBackPressed(this)

        val parentIntent = intent
        itemClickPosition = parentIntent.getIntExtra("pos", 0)
        Log.d(TAG, "$itemClickPosition")


        mainItemClickViewModel.getItemKey()

     //   alcoholCountKeyHash = FirebaseRead.imgHashMap[itemClickPosition]!!
       // test()


        binding.apply {

            mainItemViewpager.adapter = mainItemClickPagerAdapter
            mainItemViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            mainItemSpringDotsIndicator.setViewPager2(mainItemViewpager)

            mainItemViewpager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.d(TAG, "리스너 안 $position")
                    if (position == 0) {
                        mainItemBtnBack.visibility = View.GONE
                    } else if (position == 3) {
                        mainItemBtnNext.visibility = View.GONE
                        mainItemBtnExit.visibility = View.VISIBLE

                    } else {
                        mainItemBtnBack.visibility = View.VISIBLE
                        mainItemBtnNext.visibility = View.VISIBLE
                    }
                }
            })
            mainItemBtnNext.setOnClickListener {
                val currentNext = mainItemViewpager.currentItem
                mainItemViewpager.setCurrentItem(currentNext + 1, true)
            }

            mainItemBtnBack.setOnClickListener {
                val currentBack = mainItemViewpager.currentItem
                mainItemViewpager.setCurrentItem(currentBack - 1, true)
            }

            mainItemBtnExit.setOnClickListener {
                finish()
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