package com.one_day.one_drink_a_day.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.one_day.one_drink_a_day.DoubleClickBackPressed
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.ActivityAddItemTestBinding
import com.one_day.one_drink_a_day.dialog.DatePikerDialog
import com.one_day.one_drink_a_day.firebase.FirebaseDB
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView


class AddItemTest : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var countArray: Array<String>
    private lateinit var binding: ActivityAddItemTestBinding
    private lateinit var doubleClickBackPressed: DoubleClickBackPressed
    private var imgNum : Int? = null
    private val GALLERY_CODE = 101
    private val CROP_CODE = 102
    private val TAKE_PICTURE = 103
    private var date: String? = null
    private var uri : Uri? = null   // 이미지 파일 경로
    private val TAG = "AddItemTest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemTestBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var str: String
        var test: Float

        val dialog = DatePikerDialog()
        doubleClickBackPressed = DoubleClickBackPressed(this)

        countArray = resources.getStringArray(R.array.bottleCount)
        adapter = ArrayAdapter(this, R.layout.spinner_item, countArray)

        binding.apply {
            countSpinner1.adapter = adapter
            countSpinner2.adapter = adapter
            countSpinner3.adapter = adapter
            countSpinner4.adapter = adapter

            btnDateTest.setOnClickListener {
                dialog.show(supportFragmentManager, "DatePikerDialog")
            }

            btnBack.setOnClickListener {
                contentsInputTextCheck()
            }

            btnSave.setOnClickListener {
                if (date.isNullOrBlank()) {
                    Toast.makeText(this@AddItemTest, "날짜를 선택해 주세요", Toast.LENGTH_SHORT).show()
                } else {
                    // FirebaseDB.resultAdd(date!!,)
                    FirebaseDB.database.child("Users")
                        .child(FirebaseDB.userID!!)
                        .child(date!!)
                        .setValue("테스트")
                    finish()
                }
            }

                img1.setOnClickListener {
                    requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), GALLERY_CODE)
                    imgNum = 1

                }
                img2.setOnClickListener {
                    requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), GALLERY_CODE)
                    imgNum = 2
                }
                img3.setOnClickListener {
                    requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), GALLERY_CODE)
                    imgNum = 3
                }
                img4.setOnClickListener {
                    requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), GALLERY_CODE)
                    imgNum = 4
                }

                countSpinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                }
                // 엔터키 입력 방지
                titleInput.setOnKeyListener { v, keyCode, event ->
                    return@setOnKeyListener event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER
                }
            }
        }
        private fun openGallery() {
            val intent = Intent("android.intent.action.GET_CONTENT")
            intent.type = "image/*"     // 모든 이미지
            startActivityForResult(intent, GALLERY_CODE)
            Log.d("mmm ddk", CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE.toString())
        }

    private fun cropImage(uri: Uri?) {
        CropImage.activity(uri).setGuidelines(CropImageView.Guidelines.ON)  // 크롭 위한 가이드 열어서 크롭할 이미지 받아오기
            //.setCropShape(CropImageView.CropShape.RECTANGLE)   // 사각형으로 자르기
            .setMinCropResultSize(100,100)
            .setMaxCropResultSize(200,250)
            .start(this)
        // 프레그먼트에서 사용할 땐 .start(activity as 프레그먼트의 부모 Activity, this@형재 프레그먼트 이름)
    }

        private fun contentsInputTextCheck() {
            if (binding.titleInput.text.isNotBlank() || imgNum != null) {
                doubleClickBackPressed.backPressed(resources.getString(R.string.addItemBackPressedMessage))
            } else {
                finish()
            }
        }

        private fun requirePermissions(permissions: Array<String>, requestCode: Int) {
            Log.d(TAG, "권한 요청")
            // isAllPermissionsGranted : 권한이 모두 승인 되었는지 여부 저장
            // all 메서드를 사용하면 배열 속에 들어 있는 모든 값을 체크할 수 있다.
            val isAllPermissionsGranted =
                permissions.all { checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED }
            if (isAllPermissionsGranted) {
                // 권한이 이미 있으면 갤러리 실행
                openGallery()
            } else {
                // 사용자에 권한 승인 요청
                ActivityCompat.requestPermissions(this, permissions, requestCode)
            }

        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                openGallery()
            } else {
                Toast.makeText(this, "저장소 권한을 승인해야 앨범에서 이미지를 불러올 수 있습니다.", Toast.LENGTH_LONG).show()
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
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
//                CROP_CODE -> {
//                    // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
//                    // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다
//                    val pictureUri = data?.data
//                    val intent = Intent("com.android.camera.action.CROP")
//                    intent.setDataAndType(pictureUri, "image/*")
//                    intent.putExtra("outputX", 200) //크롭한 이미지 x축 크기
//                    intent.putExtra("outputY", 200) //크롭한 이미지 y축 크기
//                    intent.putExtra("aspectX", 1) //크롭 박스의 x축 비율
//                    intent.putExtra("aspectY", 1) //크롭 박스의 y축 비율
//                    intent.putExtra("scale", true)
//                    intent.putExtra("return-data", true)
//                    startActivityForResult(intent, TAKE_PICTURE)
//                }

//                TAKE_PICTURE ->{
//                    val pictureUri = data?.data
//                    try {
//                         when (imgNum) {
//                             1 -> {
//                                 Glide.with(applicationContext).load(pictureUri).into(binding.img1)
//                                 Log.d(TAG, "1번 사진 url $pictureUri")
//                             }
//                             2 -> {
//                                 Glide.with(applicationContext).load(pictureUri).into(binding.img2)
//                                 Log.d(TAG, "2번 사진 url $pictureUri")
//                             }
//                             3 -> Glide.with(applicationContext).load(pictureUri).into(binding.img3)
//                             4 -> Glide.with(applicationContext).load(pictureUri).into(binding.img4)
//                         }
//                     } catch (e: Exception) {
//                         Log.e(TAG, "이미지 가져오기 오류")
//                     }
//                }
                CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                    val result = CropImage.getActivityResult(data)
                    if (resultCode == Activity.RESULT_OK) {
                        result.uri?.let {
                            // 이미지 파일 읽어와서 설정하기
                            val bitmap = BitmapFactory.decodeStream(
                                contentResolver!!.openInputStream(result.uri!!)
                                // 프레그먼트명 activity?.contentResolver!!.openInputStream(result.uri!!)
                            )
                            when (imgNum) {
                                1 -> binding.img1.setImageBitmap(bitmap)
                                2 -> binding.img2.setImageBitmap(bitmap)
                                3 -> binding.img3.setImageBitmap(bitmap)
                                4 -> binding.img4.setImageBitmap(bitmap)
                            }
                        }
                    }
                }
            }
        }

        override fun onBackPressed() {
            contentsInputTextCheck()
        }

        fun datePickerResult(year: Int, month: Int, day: Int) {
            Log.d(TAG, "$year 년 $month 월 $day 일")
            date = "${year}/${month}월/${day}일"
            Log.d(TAG, date!!)
        }
}

