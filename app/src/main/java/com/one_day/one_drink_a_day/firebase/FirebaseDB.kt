package com.one_day.one_drink_a_day.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.one_day.one_drink_a_day.SharedObject

object FirebaseDB {
    private val TAG = "FirebaseDB"
    val firebaseAuth = FirebaseAuth.getInstance()
    val database = Firebase.database.reference
    val userID : String? = firebaseAuth.currentUser?.uid
    val userName : String? = firebaseAuth.currentUser?.displayName
    fun resultAdd(date : String){

        // 유저 개인 사진과 마신 병 개수 저장
        for (i in 0..3) {
            database.child("Users")
                .child(userID!!)
                .child("ItemList")
                .child(date)
                .child("${SharedObject.titleString}")
                .child(SharedObject.saveSpinnerCount[i])
                .child("Img")
                .setValue(SharedObject.imgStringArray[i])
        }
        // 타이틀 이미지와 타이틀 이름 저장
        database.child("Users")
            .child(userID!!)
            .child("ItemList")
            .child(date)
            .child("${SharedObject.titleString}")
            .child("TitleImg")
            .setValue(SharedObject.titleImg)

        // 전체 DB에 저장
        publicListAdd()
    }
    fun userAdd(){
        database.child("Users")
            .child(userID!!)
            .child("Name")
            .setValue(userName)
    }
    fun alcoholCheckAdd(soJo : String, beer : String, etc : String){
        database.child("Users")
            .child(userID!!)
            .child("AlcoholCheck")
            .child("SoJo")
            .setValue(soJo)
        database.child("Users")
            .child(userID)
            .child("AlcoholCheck")
            .child("Beer")
            .setValue(beer)
        database.child("Users")
            .child(userID)
            .child("AlcoholCheck")
            .child("Etc")
            .setValue(etc)
    }

    fun publicListAdd(){
        database.child("publicList")
            .child("ItemList")
            .child("${SharedObject.titleString}")
            .child("TitleImg")
            .setValue(SharedObject.titleImg)
        for (i in 0..3) {
            database.child("publicList")
                .child("ItemList")
                .child("${SharedObject.titleString}")
                .child(SharedObject.saveSpinnerCount[i])
                .child("Img")
                .setValue(SharedObject.imgStringArray[i])
        }
        database.child("publicList")
            .child("ItemList")
            .child("${SharedObject.titleString}")
            .child("UserId")
            .setValue(userID)
    }

}