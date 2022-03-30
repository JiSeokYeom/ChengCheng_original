package com.one_day.one_drink_a_day

import android.R.attr
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Base64.decode
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*


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
    fun setUriToBitmapImage(uri: Uri) : Bitmap? {
        return try {
            val inputStream : InputStream? = activity.contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            bitmap
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            null
        }
    }

  fun stringToBitmap(string: String):Bitmap?{
      return try {
          val encodeByte: ByteArray = decode(string,android.util.Base64.DEFAULT)
          val bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
          bitmap
      } catch (e : Exception){
          e.printStackTrace()
          null
      }
  }

    @RequiresApi(Build.VERSION_CODES.O)
    fun bitmapToString(bitmap: Bitmap): String?{
        return try {
            var image: String? = ""
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val byteArray = stream.toByteArray()
            image = Base64.getEncoder().encodeToString(byteArray)
            return image
        } catch (e : Exception){
            e.printStackTrace()
            null
        }
    }


    constructor( activity: Activity,fragment : Fragment) : this(activity) {
        this.fragment = fragment
    }
}