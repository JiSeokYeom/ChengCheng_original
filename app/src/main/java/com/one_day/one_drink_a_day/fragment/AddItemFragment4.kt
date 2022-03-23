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
import androidx.lifecycle.ViewModelProvider
import com.one_day.one_drink_a_day.CropLibrary
import com.one_day.one_drink_a_day.Permission
import com.one_day.one_drink_a_day.databinding.FragmentAdditem4Binding
import com.one_day.one_drink_a_day.dialog.DatePikerDialog
import com.one_day.one_drink_a_day.firebase.FirebaseDB
import com.one_day.one_drink_a_day.model.SharedObject
import com.one_day.one_drink_a_day.style.SpinnerStyle
import com.one_day.one_drink_a_day.viewmodel.DatePikerViewModel
import com.theartofdev.edmodo.cropper.CropImage

class AddItemFragment4 : Fragment() {
    private val datePikerViewModel: DatePikerViewModel by lazy {
        ViewModelProvider(this).get(DatePikerViewModel::class.java)
    }
    private lateinit var binding: FragmentAdditem4Binding
    private var date: String? = null
    private lateinit var spinnerStyle: SpinnerStyle
    private lateinit var permission: Permission
    private lateinit var cropLibrary: CropLibrary
    private val GALLERY_CODE = 101
    private var imgNum: Int? = null
    private var uri: Uri? = null   // 이미지 파일 경로
    private val TAG = "AddItemFragment4"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAdditem4Binding.inflate(inflater,container,false)

        val dialog = DatePikerDialog()

        spinnerStyle = SpinnerStyle(requireContext(),requireActivity())

        permission = Permission(requireActivity())
        cropLibrary = CropLibrary(requireActivity(),this)



        binding.apply {
            spinnerStyle.spinnerSet(countSpinner4)

            btnDateTest.setOnClickListener {
                dialog.show(childFragmentManager, "DatePikerDialog")
            }

            img4.setOnClickListener {
                if(permission.requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), GALLERY_CODE)){
                    openGallery()
                    imgNum = 1
                }
            }

            btnSave.setOnClickListener {
                if (date.isNullOrBlank()) {
                    Toast.makeText(requireActivity(), "날짜를 선택해 주세요", Toast.LENGTH_SHORT).show()
                } /*else if(){

                }*/
                else {
                    // FirebaseDB.resultAdd(date!!,)
                    FirebaseDB.database.child("Users")
                        .child(FirebaseDB.userID!!)
                        .child("ItemList")
                        .child(date!!)
                        .setValue("테스트")
                    requireActivity().finish()
                }
            }
        }
        return binding.root
    }

    private fun openGallery() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"     // 모든 이미지
        startActivityForResult(intent, GALLERY_CODE)
    }

    fun datePickerResult(year: Int, month: Int, day: Int) {
        Log.d(TAG, "$year 년 $month 월 $day 일")
        date = "${year}/${month}월/${day}일"
        Log.d(TAG, date!!)
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
                        // 이미지 파일 읽어와서 설정하기
                        val bitmap = BitmapFactory.decodeStream(
                            requireActivity().contentResolver!!.openInputStream(result.uri!!)
                            // 프레그먼트명 activity?.contentResolver!!.openInputStream(result.uri!!)
                        )
                        binding.img4.setImageBitmap(bitmap)
                        SharedObject.imgBitmapArray[3] = bitmap
                    }
                }
            }
        }
    }
}