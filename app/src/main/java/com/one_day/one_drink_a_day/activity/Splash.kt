package com.one_day.one_drink_a_day.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.one_day.one_drink_a_day.firebase.FirebaseDB
import com.one_day.one_drink_a_day.databinding.ActivitySplashBinding
import com.one_day.one_drink_a_day.model.MainRecyclerViewItem

class Splash : AppCompatActivity() {
    //lateinit var firebaseAuth: FirebaseAuth
    lateinit var binding: ActivitySplashBinding
    private val TAG = "Splash"
    private val myRef = FirebaseDB.database.child("publicList").child("ItemList")
    companion object{
        val itemArray : ArrayList<MainRecyclerViewItem> = arrayListOf()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginActivity = Intent(this, Login::class.java)
        val mainActivity = Intent(this, MainActivity::class.java)


        Log.d(TAG,"유저 존재 확인 ${FirebaseDB.userID}")
        itemListGet()
    if (FirebaseDB.userID != null)
    {
            accountAvailable(mainActivity)
    } else
    {
        accountAvailable(loginActivity)
    }

}

    private fun accountAvailable(intent: Intent) {
        Handler().postDelayed({
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
         //   overridePendingTransition(anim.fade_in, anim.fade_out)
            finish()
        }, 3000)
    }

    private fun itemListGet(){
             myRef.addListenerForSingleValueEvent(object : ValueEventListener{
               override fun onDataChange(snapshot: DataSnapshot) {
                   for (dataSnapshot : DataSnapshot in snapshot.children){
                       itemArray.add(MainRecyclerViewItem(dataSnapshot.child("TitleImg").value.toString(),dataSnapshot.child("TitleName").value.toString()))
                   }
                   Log.d(TAG,"아이템 어레이 확인 $itemArray")
               }

               override fun onCancelled(error: DatabaseError) {
                   TODO("Not yet implemented")
               }
           })
    }
}