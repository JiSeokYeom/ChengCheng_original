package com.one_day.one_drink_a_day.firebase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.one_day.one_drink_a_day.SharedObject
import java.io.ByteArrayInputStream
import java.util.*


object FirebaseDB {
    private val TAG = "FirebaseDB"
    val firebaseAuth = FirebaseAuth.getInstance()
    val database = Firebase.database.reference
    val instanceDatabase = FirebaseDatabase.getInstance()
    val userID : String? = firebaseAuth.currentUser?.uid
    val userName : String? = firebaseAuth.currentUser?.displayName
    var key : String? = null
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
        val resultKey = makeKey()
        // 유저 타이틀 이미지 추가
        database.child("publicList")
            .child("ItemList")
            .child(resultKey!!)
            .child("TitleImg")
            .setValue(SharedObject.titleImg)

        // 유저 타이틀 이름 추가
        database.child("publicList")
            .child("ItemList")
            .child(resultKey!!)
            .child("TitleName")
            .setValue(SharedObject.titleString)

        // 유저 고유 id추가
        database.child("publicList")
            .child("ItemList")
            .child(resultKey)
            .child("UserId")
            .setValue(userID)

        // 유저 개인 사진과 마신 병 개수 저장
        for (i in 0..3) {
            database.child("publicList")
                .child("ItemList")
                .child(resultKey)
                .child(SharedObject.saveSpinnerCount[i])
                .child("Img")
                .setValue(SharedObject.imgStringArray[i])
        }

    }

    private fun makeKey(): String? { //랜덤 키값 구하는 함수
        key = instanceDatabase.getReference("publicList").child("ItemList").push().key.toString()
        return key
    }


}