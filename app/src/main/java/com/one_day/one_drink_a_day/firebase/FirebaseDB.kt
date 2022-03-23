package com.one_day.one_drink_a_day.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.one_day.one_drink_a_day.model.SharedObject

object FirebaseDB {
    private val TAG = "FirebaseDB"
    val firebaseAuth = FirebaseAuth.getInstance()
    val database = Firebase.database.reference
    val userID : String? = firebaseAuth.currentUser?.uid
    val userName : String? = firebaseAuth.currentUser?.displayName
    fun resultAdd(date : String,spinnerCount : ArrayList<Int>){
        for (i in 0..3) {
            database.child("Users")
                .child(userID!!)
                .child("ItemList")
                .child(date)
                .child("${spinnerCount[i]}")
                .setValue(SharedObject.imgBitmapArray[i])
        }

    }
    fun userAdd(){
        database.child("Users")
            .child(userID!!)
            .child("Name")
            .setValue(userName)
/*        database.child("Users")
            .child(userID)
            .child("AlcoholCheck")
            .child("SoJo")
            .setValue("0")
        database.child("Users")
            .child(userID)
            .child("AlcoholCheck")
            .child("Beer")
            .setValue("0")
        database.child("Users")
            .child(userID)
            .child("AlcoholCheck")
            .child("Etc")
            .setValue("0")*/
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

}