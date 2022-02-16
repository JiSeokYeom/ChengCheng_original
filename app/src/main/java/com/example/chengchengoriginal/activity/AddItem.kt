package com.example.chengchengoriginal.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.chengchengoriginal.R
import com.example.chengchengoriginal.databinding.ActivityAddItemBinding
import com.example.chengchengoriginal.databinding.ActivityMainBinding
import java.util.zip.Inflater

class AddItem : AppCompatActivity() {
    private lateinit var binding: ActivityAddItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}