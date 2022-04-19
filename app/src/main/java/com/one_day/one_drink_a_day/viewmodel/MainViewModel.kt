package com.one_day.one_drink_a_day.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.one_day.one_drink_a_day.firebase.FirebaseDB
import com.one_day.one_drink_a_day.firebase.FirebaseRead
import com.one_day.one_drink_a_day.model.MainRecyclerViewItem

class MainViewModel : ViewModel(){
    private val TAG = "MainViewModel"
    var rvLiveData = MutableLiveData(arrayListOf<MainRecyclerViewItem>())

init {
    Log.d(TAG,"뷰모델 실행 됨")
    rvLiveData = FirebaseRead.fireBaseGetListAll()
}

}