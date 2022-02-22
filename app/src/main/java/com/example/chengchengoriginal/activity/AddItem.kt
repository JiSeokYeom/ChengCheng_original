package com.example.chengchengoriginal.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.chengchengoriginal.R
import com.example.chengchengoriginal.databinding.ActivityAddItemBinding


class AddItem : AppCompatActivity() {
    private val ALBUM_CODE = 101
    private val TAG = "AddItem"
    private lateinit var countArray : Array<String>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var binding: ActivityAddItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        countArray = resources.getStringArray(R.array.bottleCount)
       /* binding.img1.setOnClickListener {
            requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), ALBUM_CODE)
        }*/
        binding.btnBack.setOnClickListener {
            finish()
        }

        adapter = ArrayAdapter(this,R.layout.spinner_item,countArray)
        binding.sojoSpinner.adapter = adapter
        binding.beerSpinner.adapter = adapter
    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, ALBUM_CODE)
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


   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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
        }*/

    }

