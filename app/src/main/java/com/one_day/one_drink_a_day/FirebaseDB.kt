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
            .child(date)
            .child("Sojo")
            .setValue(soJoCount)
    }
}