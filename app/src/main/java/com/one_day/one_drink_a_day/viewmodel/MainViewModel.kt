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
    private var rvLiveData = MutableLiveData(arrayListOf<MainRecyclerViewItem>())
    private val myRef = FirebaseDB.database.child("publicList").child("ItemList")
     var item = arrayListOf<MainRecyclerViewItem>()


    fun getListAll():MutableLiveData<ArrayList<MainRecyclerViewItem>>{
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot : DataSnapshot in snapshot.children) {
                  //  item.add(MainRecyclerViewItem(dataSnapshot.child("TitleImg").value.toString(),dataSnapshot.child("TitleName").value.toString()))
                    rvLiveData.value?.add(MainRecyclerViewItem(dataSnapshot.child("TitleImg").value.toString(),dataSnapshot.child("TitleName").value.toString()))
                }
                rvLiveData.value = rvLiveData.value
                rvLiveData = MutableLiveData(arrayListOf())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
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