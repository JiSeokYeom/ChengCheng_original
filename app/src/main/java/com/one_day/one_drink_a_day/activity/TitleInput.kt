package com.one_day.one_drink_a_day.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.one_day.one_drink_a_day.*
import com.one_day.one_drink_a_day.databinding.ActivityTitleInputBinding
import com.theartofdev.edmodo.cropper.CropImage
import java.io.FileNotFoundException
import java.io.InputStream


class TitleInput : AppCompatActivity() {
    private lateinit var binding: ActivityTitleInputBinding
    private val TAG = "TitleInput"
    private lateinit var permission : Permission
    private lateinit var cropLibrary: CropLibrary
    private var uri : Uri? = null
    private var imgNum: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTitleInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

         val addItem = Intent(this,AddItem::class.java)
        permission = Permission(this)
        cropLibrary = CropLibrary(this)

        binding.apply {

            titleImg.setOnClickListener {
                if(permission.requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), SharedObject.GALLERY_CODE)){
                    openGallery()
                    imgNum = 1
                }
            }

            btnTitleNext.setOnClickListener {
                if (imgNum != 1) {
                    Toast.makeText(this@TitleInput, "대표 이미지를 저장해 주세요", Toast.LENGTH_SHORT).show()
                }else if(binding.titleInput.text.isEmpty()){
                    Toast.makeText(this@TitleInput, "제목을 입력해 주세요", Toast.LENGTH_SHORT).show()
                }
                else {
                    SharedObject.titleString = titleInput.text.toString()
                    startActivity(addItem)
                    finish()
                }
            }

        }
    }
    private fun openGallery() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"     // 모든 이미지
        startActivityForResult(intent, SharedObject.GALLERY_CODE)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SharedObject.GALLERY_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    uri = data?.data    // 선택한 이미지의 주소
                    // 사용자가 이미지를 선택했으면(null이 아니면)
                    if (uri != null) {
                        cropLibrary.activityCropImage(uri)
                    }
                }
            }
            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                val result = CropImage.getActivityResult(data)
                if (resultCode == Activity.RESULT_OK) {
                    result.uri?.let {
                        SharedObject.titleImg = cropLibrary.bitmapToString(cropLibrary.setUriToBitmapImage(result.uri)!!)
                        Log.d(TAG,"공유객체 비트맵 주소 ${SharedObject.titleImg}")
                        Log.d(TAG,"공유객체 비트맵 주소2 ${cropLibrary.stringToBitmap(SharedObject.titleImg!!)}")
                        binding.titleImg.setImageBitmap(cropLibrary.setUriToBitmapImage(result.uri))
                    }
                }
            }
        }
    }
}

