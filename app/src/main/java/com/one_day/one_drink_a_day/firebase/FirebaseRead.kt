package com.one_day.one_drink_a_day.firebase

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

object FirebaseRead {
    private val TAG = "FirebaseRead"
    private val myRef = FirebaseDB.database.child("publicList").child("ItemList")
    private var cnt = 0
    var imgHashMap : HashMap<Int,HashMap<String,String>> = hashMapOf()


    fun getClickImgListAll() {
        cnt = 0
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot : DataSnapshot in snapshot.children) {
                    Log.d(TAG, "${dataSnapshot.child("ImgList").value}")
                    imgHashMap[cnt] = dataSnapshot.child("ImgList").value as HashMap<String, String>
                    cnt++
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG,"데이터 가져오기 오류")
            }
        })
    }


}