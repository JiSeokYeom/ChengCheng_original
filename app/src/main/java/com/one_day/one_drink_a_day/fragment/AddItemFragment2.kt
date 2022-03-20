package com.one_day.one_drink_a_day.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.one_day.one_drink_a_day.Permission
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.FragmentAdditem2Binding
import com.one_day.one_drink_a_day.style.SpinnerStyle

class AddItemFragment2 : Fragment() {
    private lateinit var binding: FragmentAdditem2Binding
    private lateinit var spinnerStyle: SpinnerStyle
    private lateinit var permission: Permission
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAdditem2Binding.inflate(inflater,container,false)



        return binding.root
    }


}