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
        var alcoholCount: ArrayList<String> = arrayListOf("tmp","tmp","tmp","tmp")
    }

   fun getItemKey(){
       alcoholCountKeyHash = FirebaseRead.imgHashMap[itemClickPosition]!!
        for (key: String in alcoholCountKeyHash.keys) {
            Log.d(TAG, "해쉬맵의 키들 ${alcoholCountKeyHash.keys}")
            when (key) {
                "1병" -> alcoholCount[0] = alcoholCountKeyHash["1병"].toString()
                "2병" -> alcoholCount[3] = alcoholCountKeyHash["2병"].toString()
                "3병" -> alcoholCount[2] = alcoholCountKeyHash["3병"].toString()
                else -> alcoholCount[1] = alcoholCountKeyHash["4병"].toString()
            }
        }

       for (i in 0 until alcoholCount.size){
           Log.d(TAG,"카운트 안에 값 ${alcoholCount[i]}")
       }
    }


}