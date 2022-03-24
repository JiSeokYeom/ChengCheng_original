package com.one_day.one_drink_a_day.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.one_day.one_drink_a_day.CropLibrary
import com.one_day.one_drink_a_day.Permission
import com.one_day.one_drink_a_day.databinding.FragmentAdditem1Binding
import com.one_day.one_drink_a_day.SharedObject
import com.one_day.one_drink_a_day.style.SpinnerStyle
import com.theartofdev.edmodo.cropper.CropImage

class AddItemFragment1 : Fragment() {
    private lateinit var spinnerStyle: SpinnerStyle
    private lateinit var binding: FragmentAdditem1Binding
    private lateinit var permission: Permission
    private lateinit var cropLibrary: CropLibrary
    private var imgNum: Int? = null
    private val GALLERY_CODE = 101
    private var uri: Uri? = null   // 이미지 파일 경로
    private val TAG = "AddItemFragment1"
    private lateinit var imgBitmap : Bitmap


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdditem1Binding.inflate(inflater, container, false)

        spinnerStyle = SpinnerStyle(requireContext(),requireActivity())

        permission = Permission(requireActivity())
        cropLibrary = CropLibrary(requireActivity(),this)

        binding.apply {
            spinnerStyle.spinnerSet(countSpinner1)

            img1.setOnClickListener {
                   if(permission.requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), GALLERY_CODE)){
                    openGallery()
                    imgNum = 1
                }
            }

            SharedObject.spinnerSelect(countSpinner1,0)
    /*        countSpinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d(TAG, "${countArray[position]} 선택됨")
                    str = countArray[position].replace("병", "")
                    Log.d(TAG, "$str 숫자 변환 값")
                    test = str.toFloat() + str.toFloat()
                    Log.d(TAG, "$test 숫자 변환 값2")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }*/
        }
        return binding.root
    }

    private fun openGallery() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"     // 모든 이미지
        startActivityForResult(intent, GALLERY_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GALLERY_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    uri = data?.data    // 선택한 이미지의 주소
                    // 사용자가 이미지를 선택했으면(null이 아니면)
                    if (uri != null) {
                        cropLibrary.cropImage(uri)
                    }
                }
            }
            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                val result = CropImage.getActivityResult(data)
                if (resultCode == Activity.RESULT_OK) {
                    result.uri?.let {
                        // 이미지 파일 읽어와서 이미지뷰에 띄워주기
                        SharedObject.imgStringArray[0] = it.toString()
                        Glide.with(this)
                            .load(it)
                            .into(binding.img1)
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"포즈상태")
        //SharedObject.imgBitmapArray[0] = imgBitmap
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"온리즘")
    }
}