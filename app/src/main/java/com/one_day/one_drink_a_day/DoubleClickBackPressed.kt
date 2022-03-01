package com.one_day.one_drink_a_day

import android.app.Activity
import android.widget.Toast

class DoubleClickBackPressed(context: Activity) {
    private var backPressedTime: Long = 0
    private val activity = context

    fun backPressed(textMsg : String) {
        if (System.currentTimeMillis() > backPressedTime + 2000) {
            backPressedTime = System.currentTimeMillis()
            Toast.makeText(activity, textMsg, Toast.LENGTH_SHORT).show()
            return
        }
      if(System.currentTimeMillis() <= backPressedTime + 2000) {
          activity.finish()
        }
    }
}