package com.one_day.one_drink_a_day.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.one_day.one_drink_a_day.DoubleClickBackPressed
import com.one_day.one_drink_a_day.Permission
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.FragmentAdditem1Binding
import com.one_day.one_drink_a_day.dialog.DatePikerDialog
import com.one_day.one_drink_a_day.firebase.FirebaseDB
import com.one_day.one_drink_a_day.style.SpinnerStyle
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class AddItemFragment1 : Fragment() {
    private lateinit var spinnerStyle: SpinnerStyle
    private lateinit var binding: FragmentAdditem1Binding
    private lateinit var permission: Permission
    private var imgNum: Int? = null
    private val GALLERY_CODE = 101
    private var uri: Uri? = null   // 이미지 파일 경로
    private val TAG = "AddItemFragment1"

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdditem1Binding.inflate(inflater, container, false)

        var str: String
        var test: Float

        spinnerStyle = SpinnerStyle(requireContext(),requireActivity())

        permission = Permission(requireActivity())

        binding.apply {
            spinnerStyle.spinnerSet(countSpinner1)

            img1.setOnClickListener {
                   if(permission.requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), GALLERY_CODE)){
                    openGallery()
                    imgNum = 1
                }
                else{
                    permission.requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), GALLERY_CODE)
                }
            }

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

    private fun cropImage(uri: Uri?) {
        CropImage.activity(uri).setGuidelines(CropImageView.Guidelines.ON)  // 크롭 위한 가이드 열어서 크롭할 이미지 받아오기
            //.setCropShape(CropImageView.CropShape.RECTANGLE)   // 사각형으로 자르기
            .setMinCropResultSize(1000,1300)
            .setMaxCropResultSize(1500,1900)
            .start(requireActivity(),this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GALLERY_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    uri = data?.data    // 선택한 이미지의 주소
                    // 사용자가 이미지를 선택했으면(null이 아니면)
                    if (uri != null) {
                        cropImage(uri)
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
                        binding.img1.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }
}