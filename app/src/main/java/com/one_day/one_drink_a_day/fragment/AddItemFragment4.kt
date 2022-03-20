package com.one_day.one_drink_a_day.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.FragmentAdditem4Binding
import com.one_day.one_drink_a_day.dialog.DatePikerDialog
import com.one_day.one_drink_a_day.firebase.FirebaseDB

class AddItemFragment4 : Fragment() {
    private lateinit var binding: FragmentAdditem4Binding
    private var date: String? = null
    private val TAG = "AddItemFragment4"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAdditem4Binding.inflate(inflater,container,false)

        val dialog = DatePikerDialog()

        binding.apply {
            btnDateTest.setOnClickListener {
                dialog.show(childFragmentManager, "DatePikerDialog")
            }

            btnSave.setOnClickListener {
                if (date.isNullOrBlank()) {
                    Toast.makeText(requireActivity(), "날짜를 선택해 주세요", Toast.LENGTH_SHORT).show()
                } else {
                    // FirebaseDB.resultAdd(date!!,)
                    FirebaseDB.database.child("Users")
                        .child(FirebaseDB.userID!!)
                        .child(date!!)
                        .setValue("테스트")
                    requireActivity().finish()
                }
            }
        }



        return binding.root

    }
    fun datePickerResult(year: Int, month: Int, day: Int) {
        Log.d(TAG, "$year 년 $month 월 $day 일")
        date = "${year}/${month}월/${day}일"
        Log.d(TAG, date!!)
    }

}