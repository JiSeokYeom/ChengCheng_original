package com.one_day.one_drink_a_day.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.one_day.one_drink_a_day.firebase.FirebaseDB
import com.one_day.one_drink_a_day.model.MainRecyclerViewItem

class MainViewModel : ViewModel(){
   private var rvLiveData : MutableLiveData<ArrayList<MainRecyclerViewItem>> = MutableLiveData<ArrayList<MainRecyclerViewItem>>()
    private var item = ArrayList<MainRecyclerViewItem>()


    fun getListAll(): MutableLiveData<ArrayList<MainRecyclerViewItem>>{
        val myRef = FirebaseDB.database.child("publicList").child("ItemList")
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot : DataSnapshot in snapshot.children) {
                    item.add(MainRecyclerViewItem(dataSnapshot.child("TitleImg").toString(),dataSnapshot.child("TitleName").toString()))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        rvLiveData.value = item
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