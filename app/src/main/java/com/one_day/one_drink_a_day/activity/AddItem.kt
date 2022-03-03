package com.one_day.one_drink_a_day.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.one_day.one_drink_a_day.DoubleClickBackPressed
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.ActivityAddItemBinding
import com.one_day.one_drink_a_day.dialog.DatePikerDialog
import com.one_day.one_drink_a_day.viewmodel.AddItemViewModel


class AddItem : AppCompatActivity() {
    private val viewModel: AddItemViewModel by lazy {
        ViewModelProvider(this).get(AddItemViewModel::class.java)
    }
    private val ALBUM_CODE = 101
    private val TAG = "AddItem"
    private var date : String? = null
    private val user = Firebase.auth.currentUser
    private lateinit var database: DatabaseReference
    private lateinit var countArray : Array<String>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var binding: ActivityAddItemBinding
    private lateinit var doubleClickBackPressed : DoubleClickBackPressed
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addItemViewModel = viewModel
        binding.lifecycleOwner = this

        database = Firebase.database.reference

        val dialog = DatePikerDialog()
        doubleClickBackPressed = DoubleClickBackPressed(this)

        countArray = resources.getStringArray(R.array.bottleCount)
        adapter = ArrayAdapter(this, R.layout.spinner_item, countArray)

       // val userId = user?.uid
       // Log.d(TAG, userId.toString())
        binding.apply {
            img1.setOnClickListener {
                requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), ALBUM_CODE)
            }

            btnBack.setOnClickListener {
                contentsInputTextCheck()
            }

            btnDate.setOnClickListener {
                dialog.show(supportFragmentManager, "DatePikerDialog")
            }

            btnSave.setOnClickListener {
                if (date.isNullOrBlank()) {
                    Toast.makeText(this@AddItem, "날짜를 선택해 주세요", Toast.LENGTH_SHORT).show()
                }
                else{
                /*    database.child("users")
                        .child(user!!.uid)
                        .child(date!!)
                        .setValue("테스트")*/
                    finish()
                }


            }
            // editText 엔터 입력 방지
            contentsInput.setOnKeyListener { v, keyCode, event ->
                return@setOnKeyListener event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER
            }

            // editText 글자수 세기
            contentsInput.addTextChangedListener {
                viewModel.letterInputCount(it!!.length)
            }


            sojoSpinner.adapter = adapter
            beerSpinner.adapter = adapter
            etcSpinner.adapter = adapter

            // 기억이 안날때 스피너의 값을 0으로 초기화
            checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
                if (checkBox.isChecked)
                {
                    sojoSpinner.setSelection(0)
                    beerSpinner.setSelection(0)
                    etcSpinner.setSelection(0)
                }
            }
        }



    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, ALBUM_CODE)
    }
    private fun contentsInputTextCheck(){
        if(binding.contentsInput.text.isNotBlank()){
            doubleClickBackPressed.backPressed(resources.getString(R.string.addItemBackPressedMessage))
        }
        else{
            finish()
        }
    }

    private fun requirePermissions(permissions : Array<String>, requestCode: Int) {
        Log.d(TAG,"권한 요청")
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
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            openGallery()
        } else {
            Toast.makeText(this, "저장소 권한을 승인해야 앨범에서 이미지를 불러올 수 있습니다.", Toast.LENGTH_LONG).show()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            if(resultCode == RESULT_OK)
            {
                data?.data.let { uri ->
                    binding.img1.setImageURI(uri)
                }
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show()
            }
        }


    override fun onBackPressed() {
        contentsInputTextCheck()
        }
    
    fun datePickerResult(year : Int, month : Int, day : Int){
        Log.d(TAG,"$year 년 $month 월 $day 일")
        date = "$year/$month/$day"
        Log.d(TAG,date!!)
        }
    }

