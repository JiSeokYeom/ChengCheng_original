package com.one_day.one_drink_a_day

import android.app.Activity
import android.net.Uri
import androidx.fragment.app.Fragment
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import java.net.URL

class CropLibrary(val activity: Activity) {
    var fragment : Fragment? = null
    fun activityCropImage(uri: Uri?) {
        CropImage.activity(uri).setGuidelines(CropImageView.Guidelines.ON)  // 크롭 위한 가이드 열어서 크롭할 이미지 받아오기
            .setMinCropResultSize(1000,1300)
            .setMaxCropResultSize(1500,1900)
            .start(activity)
    }
    fun fragmentCropImage(uri: Uri?) {
        CropImage.activity(uri).setGuidelines(CropImageView.Guidelines.ON)  // 크롭 위한 가이드 열어서 크롭할 이미지 받아오기
            .setMinCropResultSize(1000,1300)
            .setMaxCropResultSize(1500,1900)
            .start(activity,fragment!!)
    }
    constructor( activity: Activity,fragment : Fragment) : this(activity) {
        this.fragment = fragment
    }
}