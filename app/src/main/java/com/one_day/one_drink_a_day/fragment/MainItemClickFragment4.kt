package com.one_day.one_drink_a_day.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.one_day.one_drink_a_day.CropLibrary
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.FragmentMainItemClick4Binding
import com.one_day.one_drink_a_day.viewmodel.MainItemClickViewModel

class MainItemClickFragment4 : Fragment() {
    private lateinit var cropLibrary: CropLibrary
    private lateinit var parentTextView : TextView
    private val TAG = "MainItemClickFragment4"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainItemClick4Binding.inflate(inflater,container,false)

        cropLibrary = CropLibrary(requireActivity())

        parentTextView = requireActivity().findViewById(R.id.main_item_count)


        binding.frgImg4.setImageBitmap(cropLibrary.stringToBitmap(MainItemClickViewModel.alcoholCount[1]))

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        parentTextView.text = "4병"

    }

}