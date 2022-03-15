package com.one_day.one_drink_a_day.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.one_day.one_drink_a_day.DoubleClickBackPressed
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.ActivityAddItemTestBinding
import com.one_day.one_drink_a_day.dialog.DatePikerDialog
import com.one_day.one_drink_a_day.firebase.FirebaseDB
import com.one_day.one_drink_a_day.viewmodel.AddItemTestViewModel


class AddItemTest : AppCompatActivity() {
    private val viewModel: AddItemTestViewModel by lazy {
        ViewModelProvider(this).get(AddItemTestViewModel::class.java)
    }
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var countArray: Array<String>
    private lateinit var binding: ActivityAddItemTestBinding
    private lateinit var doubleClickBackPressed: DoubleClickBackPressed
    private var imgNum : Int? = null
    private val REQUEST_CODE = 101
    private var date: String? = null
    private val TAG = "AddItemTest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addItemTestViewModel = viewModel
        binding.lifecycleOwner = this

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
                    requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE)
                    imgNum = 1

                }
                img2.setOnClickListener {
                    requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE)
                    imgNum = 2
                }
                img3.setOnClickListener {
                    requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE)
                    imgNum = 3
                }
                img4.setOnClickListener {
                    requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE)
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
           /* val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(intent, ALBUM_CODE)*/

           // startActivityForResult(intent,REQUEST_CODE)

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(intent, REQUEST_CODE)
        }

        private fun contentsInputTextCheck() {
            if (binding.titleInput.text.isNotBlank()) {
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
            if(requestCode == REQUEST_CODE){
                if(resultCode == RESULT_OK){
                    try {
                        val url = data?.data
                        when(imgNum){
                            1 -> Glide.with(applicationContext).load(url).into(binding.img1)
                            2 -> Glide.with(applicationContext).load(url).into(binding.img2)
                            3 -> Glide.with(applicationContext).load(url).into(binding.img3)
                            4 -> Glide.with(applicationContext).load(url).into(binding.img4)
                        }
                    } catch(e : Exception){
                        Log.e(TAG,"이미지 가져오기 오류")
                    }
                }
            }
           /* if (resultCode == RESULT_OK) {
                data?.data.let { uri ->
                    binding.img1.setImageURI(uri)
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show()
            }*/
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

