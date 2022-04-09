package com.one_day.one_drink_a_day.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.one_day.one_drink_a_day.firebase.FirebaseDB
import com.one_day.one_drink_a_day.model.MainRecyclerViewItem

class MainViewModel : ViewModel(){
    private val TAG = "MainViewModel"
    private var rvLiveData = MutableLiveData(arrayListOf<MainRecyclerViewItem>())
    private val myRef = FirebaseDB.database.child("publicList").child("ItemList")
    var itemCnt = 0
     var item = arrayListOf<MainRecyclerViewItem>()


    fun getListAll():MutableLiveData<ArrayList<MainRecyclerViewItem>>{
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot : DataSnapshot in snapshot.children) {
                    rvLiveData.value?.add(MainRecyclerViewItem(dataSnapshot.child("TitleImg").value.toString(),dataSnapshot.child("TitleName").value.toString()))
                }
                rvLiveData.value = rvLiveData.value
                rvLiveData = MutableLiveData(arrayListOf())
                Log.d(TAG,"아이템 총 개수 $itemCnt")
            }

            override fun onCancelled(error: DatabaseError) {
            Log.d(TAG,"데이터 가져오기 오류")
            }
        })

        Log.d("뷰모델","아이템 확인 $item")
        return rvLiveData
    }

    fun dataAdd(mainRecyclerViewItem: MainRecyclerViewItem){
      //  datas.add(mainRecyclerViewItem)
     //   dataLiveData.value = datas
    }

    fun dataDelete(mainRecyclerViewItem: MainRecyclerViewItem){
      //  datas.remove(mainRecyclerViewItem)
    }

}