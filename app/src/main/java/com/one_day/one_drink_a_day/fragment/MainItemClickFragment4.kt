package com.one_day.one_drink_a_day.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.one_day.one_drink_a_day.databinding.FragmentMainItemClick4Binding

class MainItemClickFragment4 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainItemClick4Binding.inflate(inflater,container,false)


        return binding.root
    }


}