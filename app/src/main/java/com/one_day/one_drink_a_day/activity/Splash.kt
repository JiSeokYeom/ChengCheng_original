package com.one_day.one_drink_a_day.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.one_day.one_drink_a_day.firebase.FirebaseDB
import com.one_day.one_drink_a_day.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    //lateinit var firebaseAuth: FirebaseAuth
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginActivity = Intent(this, Login::class.java)
        val mainActivity = Intent(this, MainActivity::class.java)
        var splashFlag = false


    if (FirebaseDB.userID != null)
    {
        binding.splashLayout.setOnClickListener {
            splashFlag = true
            accountAvailable(mainActivity, splashFlag)
        }
    } else
    {
        accountAvailable(loginActivity, splashFlag)
    }

}

    private fun accountAvailable(intent: Intent, splashFlag: Boolean) {
        val duration: Long = if(splashFlag) 300 else 3000
        Handler().postDelayed({
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
         //   overridePendingTransition(anim.fade_in, anim.fade_out)
            finish()
        }, duration)
    }
}