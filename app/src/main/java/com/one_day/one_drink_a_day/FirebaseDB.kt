package com.one_day.one_drink_a_day

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object FirebaseDB {
    private val firebaseAuth = FirebaseAuth.getInstance()
    val database = Firebase.database.reference
    val userID : String? = firebaseAuth.currentUser?.uid
    val userName : String? = firebaseAuth.currentUser?.displayName

    fun resultAdd(date : String,soJoCount : Int, beerCount : Int, etcCount : Int){
        database.child("Users")
            .child(userID!!)
            .child("ItemList")
            .child(date)
            .child("Sojo")
            .setValue(soJoCount)
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