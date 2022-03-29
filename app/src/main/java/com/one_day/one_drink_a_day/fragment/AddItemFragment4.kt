package com.one_day.one_drink_a_day.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.one_day.one_drink_a_day.CropLibrary
import com.one_day.one_drink_a_day.Permission
import com.one_day.one_drink_a_day.databinding.FragmentAdditem4Binding
import com.one_day.one_drink_a_day.dialog.DatePikerDialog
import com.one_day.one_drink_a_day.firebase.FirebaseDB
import com.one_day.one_drink_a_day.SharedObject
import com.one_day.one_drink_a_day.style.SpinnerStyle
import com.one_day.one_drink_a_day.viewmodel.DatePikerViewModel
import com.theartofdev.edmodo.cropper.CropImage

class AddItemFragment4 : Fragment() {
    private lateinit var binding: FragmentAdditem4Binding
  //  private lateinit var date: String
    private lateinit var spinnerStyle: SpinnerStyle
    private lateinit var permission: Permission
    private lateinit var cropLibrary: CropLibrary
    private var imgNum: Int? = null
    private var uri: Uri? = null   // 이미지 파일 경로
    private val TAG = "AddItemFragment4"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdditem4Binding.inflate(inflater, container, false)

        val dialog = DatePikerDialog()

        spinnerStyle = SpinnerStyle(requireContext(), requireActivity())

        permission = Permission(requireActivity())
        cropLibrary = CropLibrary(requireActivity(), this)


        binding.apply {
            spinnerStyle.spinnerSet(countSpinner4)
            SharedObject.spinnerSelect(countSpinner4, 3)

            btnDateTest.setOnClickListener {
                dialog.show(childFragmentManager, "DatePikerDialog")
            }

            img4.setOnClickListener {
                if (permission.requirePermissions(
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        SharedObject.GALLERY_CODE
                    )
                ) {
                    openGallery()
                    imgNum = 1
                }
            }

            btnSave.setOnClickListener {
                SharedObject.apply {
                    for (i in 0..3) {
                        if (imgBitmapArray.isNullOrEmpty()) {
                            Toast.makeText(
                                requireActivity(),
                                "이미지를 4개 다 적용 시켜주세요",
                                Toast.LENGTH_SHORT
                            ).show()
                            break
                        } else if (date.isNullOrEmpty()) {
                            Toast.makeText(
                                requireActivity(),
                                "날짜를 설정 해주세요",
                                Toast.LENGTH_SHORT
                            ).show()
                            break
                        }
                        else {
                            FirebaseDB.resultAdd(date!!)
                            imgBitmapArray = arrayListOf()
                            requireActivity().finish()
                            break
                        }
                    }

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

    fun datePickerResult(year: Int, month: Int, day: Int) {
        Log.d(TAG, "$year 년 $month 월 $day 일")
        SharedObject.date = "${year}/${month}월 ${day}일"
        Log.d(TAG, SharedObject.date ?: "Null")
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
                        SharedObject.imgBitmapArray.add(cropLibrary.setUriToBitmapImage(result.uri)!!)
                        binding.img4.setImageBitmap(SharedObject.imgBitmapArray[3])
                    }
                }
            }
        }
    }
}