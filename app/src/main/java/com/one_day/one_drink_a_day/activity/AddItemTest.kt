package com.one_day.one_drink_a_day.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.one_day.one_drink_a_day.DoubleClickBackPressed
import com.one_day.one_drink_a_day.FirebaseDB
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.ActivityAddItemBinding
import com.one_day.one_drink_a_day.databinding.ActivityAddItemTestBinding
import com.one_day.one_drink_a_day.dialog.DatePikerDialog
import com.one_day.one_drink_a_day.viewmodel.AddItemViewModel


class AddItemTest : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var countArray : Array<String>
    private lateinit var binding : ActivityAddItemTestBinding
    private val TAG = "AddItemTest"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var str : String
        var test : Float
        countArray = resources.getStringArray(R.array.bottleCount)
        adapter = ArrayAdapter(this, R.layout.spinner_item, countArray)

        binding.apply {
            countSpinner1.adapter = adapter
            countSpinner2.adapter = adapter
            countSpinner3.adapter = adapter
            countSpinner4.adapter = adapter

            countSpinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d(TAG,"${countArray[position]} 선택됨")
                    str = countArray[position].replace("병","")
                    Log.d(TAG,"$str 숫자 변환 값")
                    test = str.toFloat() + str.toFloat()
                    Log.d(TAG,"$test 숫자 변환 값2")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }
/*    private fun openGallery() {
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
        date = "${year}/${month}월/${day}일"
        Log.d(TAG,date!!)
        }*/
    }

