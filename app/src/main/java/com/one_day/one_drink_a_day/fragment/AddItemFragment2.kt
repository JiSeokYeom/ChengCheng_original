package com.one_day.one_drink_a_day.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.one_day.one_drink_a_day.CropLibrary
import com.one_day.one_drink_a_day.Permission
import com.one_day.one_drink_a_day.databinding.FragmentAdditem2Binding
import com.one_day.one_drink_a_day.SharedObject
import com.one_day.one_drink_a_day.style.SpinnerStyle
import com.theartofdev.edmodo.cropper.CropImage

class AddItemFragment2 : Fragment() {
    private lateinit var binding: FragmentAdditem2Binding
    private lateinit var spinnerStyle: SpinnerStyle
    private lateinit var permission: Permission
    private lateinit var cropLibrary: CropLibrary
    private var imgNum: Int? = null
    private var uri: Uri? = null   // 이미지 파일 경로
    private val TAG = "AddItemFragment2"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAdditem2Binding.inflate(inflater,container,false)

        spinnerStyle = SpinnerStyle(requireContext(),requireActivity())

        permission = Permission(requireActivity())
        cropLibrary = CropLibrary(requireActivity(),this)
        binding.apply {
            spinnerStyle.spinnerSet(countSpinner2)
            SharedObject.spinnerSelect(countSpinner2,1)

            img2.setOnClickListener {
                if(permission.requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), SharedObject.GALLERY_CODE)){
                    openGallery()
                    imgNum = 1
                }
            }

        }

        return binding.root
    }

    private fun openGallery() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"     // 모든 이미지
        startActivityForResult(intent, SharedObject.GALLERY_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SharedObject.GALLERY_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    uri = data?.data    // 선택한 이미지의 주소
                    // 사용자가 이미지를 선택했으면(null이 아니면)
                    if (uri != null) {
                        cropLibrary.fragmentCropImage(uri)
                    }
                }
            }
            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                val result = CropImage.getActivityResult(data)
                if (resultCode == Activity.RESULT_OK) {
                    result.uri?.let {
                        // 이미지 파일 읽어와서 이미지뷰에 띄워주기
                        SharedObject.imgStringArray[1] = it.toString()
                        Glide.with(this)
                            .load(it)
                            .into(binding.img2)
                    }
                }
            }
        }
    }

}