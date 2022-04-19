package com.one_day.one_drink_a_day.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.one_day.one_drink_a_day.CropLibrary
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.FragmentMainItemClick2Binding
import com.one_day.one_drink_a_day.viewmodel.MainItemClickViewModel

class MainItemClickFragment2 : Fragment() {
    private lateinit var parentTextView : TextView
    private lateinit var cropLibrary : CropLibrary
    private val TAG = "MainItemClickFragment2"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainItemClick2Binding.inflate(inflater,container,false)

        cropLibrary = CropLibrary(requireActivity())

        parentTextView = requireActivity().findViewById(R.id.main_item_count)
        //binding.frgImg2.setImageBitmap(cropLibrary.stringToBitmap(MainItemClickViewModel.alcoholCount[1]))


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        parentTextView.text = "테스트요2"
        Log.d(TAG,"프래그2")
    }

}