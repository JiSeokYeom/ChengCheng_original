package com.one_day.one_drink_a_day.fragment

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.activity.MainItemClick
import com.one_day.one_drink_a_day.databinding.FragmentMainItemClick1Binding
import com.one_day.one_drink_a_day.firebase.FirebaseRead
import org.w3c.dom.Text

class MainItemClickFragment1 : Fragment() {
    private lateinit var parentTextView : TextView
    private val TAG = "MainItemClickFragment1"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainItemClick1Binding.inflate(inflater,container,false)

        parentTextView = requireActivity().findViewById(R.id.main_item_count)

        Log.d(TAG,"${FirebaseRead.test[MainItemClick.itemClickPosition]?.keys}")


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        parentTextView.text = "테스트요"
        Log.d(TAG,"프래그 1")
    }
}