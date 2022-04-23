package com.one_day.one_drink_a_day.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bumptech.glide.util.Util
import com.one_day.one_drink_a_day.activity.MainItemClick.Companion.itemClickPosition
import com.one_day.one_drink_a_day.firebase.FirebaseRead

class MainItemClickViewModel : ViewModel() {
    private var alcoholCountKeyHash: HashMap<String, String> = hashMapOf()
    private val TAG = "MainItemClickViewModel"

    companion object{
        var alcoholCount: ArrayList<String> = arrayListOf()
    }

   fun getItemKey(){
       alcoholCountKeyHash = FirebaseRead.imgHashMap[itemClickPosition]!!
       alcoholCount.clear()
        for (key: String in alcoholCountKeyHash.keys) {
            Log.d(TAG, "해쉬맵의 키들 ${alcoholCountKeyHash.keys}")
            when (key) {
                "0병" -> alcoholCount.add(alcoholCountKeyHash["0병"].toString())
                "1병" -> alcoholCount.add(alcoholCountKeyHash["1병"].toString())
                "2병" -> alcoholCount.add(alcoholCountKeyHash["2병"].toString())
                "3병" -> alcoholCount.add(alcoholCountKeyHash["3병"].toString())
                "4병" -> alcoholCount.add(alcoholCountKeyHash["4병"].toString())
                else -> alcoholCount.add(alcoholCountKeyHash["5병이상"].toString())
            }
        }

       for (i in 0 until alcoholCount.size){
           Log.d(TAG,"카운트 안에 값 ${alcoholCount[i]}")
       }
    }


}