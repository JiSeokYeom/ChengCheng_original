package com.one_day.one_drink_a_day

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object FirebaseDB {
    private val firebaseAuth = FirebaseAuth.getInstance()
    val database = Firebase.database.reference
    val userID : String? = firebaseAuth.currentUser?.uid
    val userName : String? = firebaseAuth.currentUser?.displayName
    private val myRef = database.ref

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

    fun dateRead() {

        myRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // for (snapshot : DataSnapshot in snapshot.child("User").child())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}