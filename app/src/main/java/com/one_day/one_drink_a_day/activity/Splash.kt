package com.one_day.one_drink_a_day.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    lateinit var firebaseAuth : FirebaseAuth
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginActivity = Intent(this, Login::class.java)
        val mainActivity = Intent(this, MainActivity::class.java)

        firebaseAuth = FirebaseAuth.getInstance()
        if(firebaseAuth.currentUser?.uid != null){
            Log.d("aaa", firebaseAuth.currentUser?.uid!!)
        }

        binding.splashLayout.setOnClickListener {
            finish()
            startActivity(mainActivity)
        }


    }
}