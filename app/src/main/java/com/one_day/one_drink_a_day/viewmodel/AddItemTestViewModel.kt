package com.one_day.one_drink_a_day.viewmodel

import android.net.Uri
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.one_day.one_drink_a_day.R

class AddItemTestViewModel : ViewModel() {
     var firstImg : Int? = null

    init {
       firstImg = R.drawable.icon_image
    }




}