package com.one_day.one_drink_a_day.firebase

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.one_day.one_drink_a_day.model.MainRecyclerViewItem

object FirebaseRead {
    private val TAG = "FirebaseRead"
    private val myRef = FirebaseDB.database.child("publicList").child("ItemList")
    private var cnt = 0
    var mainRvLiveData = MutableLiveData(arrayListOf<MainRecyclerViewItem>())
    var imgHashMap: HashMap<Int, HashMap<String, String>> = hashMapOf()


    fun getClickImgListAll() {
        cnt = 0
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot: DataSnapshot in snapshot.children) {
                    imgHashMap[cnt] = dataSnapshot.child("ImgList").value as HashMap<String, String>
                    Log.d(TAG, "키 값 ${imgHashMap[cnt]?.keys}")
                    cnt++
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "데이터 가져오기 오류")
            }
        })
    }



    // 메인 리사이클러뷰 파이어베이스에서 아이템 읽어와서 가져오는 함수
    fun fireBaseGetListAll(): MutableLiveData<ArrayList<MainRecyclerViewItem>> {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot: DataSnapshot in snapshot.children) {
                    mainRvLiveData.value?.add(
                        MainRecyclerViewItem(
                            dataSnapshot.child("TitleImg").value.toString(),
                            dataSnapshot.child("TitleName").value.toString()
                        )
                    )
                }
                mainRvLiveData.value = mainRvLiveData.value
                mainRvLiveData = MutableLiveData(arrayListOf())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "데이터 가져오기 오류")
            }
        })
        return mainRvLiveData
    }

}