package com.example.chengchengoriginal.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.chengchengoriginal.databinding.DatepikerBinding
import com.example.chengchengoriginal.viewmodel.DatePikerViewModel
import com.example.chengchengoriginal.viewmodel.MainViewModel
import java.time.Month
import java.time.Year
import java.util.*

class DatePikerDialog : DialogFragment() {
    private val viewModel: DatePikerViewModel by lazy {
        ViewModelProvider(this).get(DatePikerViewModel::class.java)
    }
    private lateinit var binding: DatepikerBinding
    private val TAG = "DatePikerDialog"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DatepikerBinding.inflate(inflater,container,false)
        val view = binding.root

        val calendar = Calendar.getInstance()
        val cYear = calendar.get(Calendar.YEAR)
        val cMonth = calendar.get(Calendar.MONTH)
        val cDay = calendar.get(Calendar.DAY_OF_MONTH)
        Log.d(TAG,"연도 : $cYear 월 : $cMonth 일 : $cDay")

        binding.datePikerViewModel = viewModel
        binding.lifecycleOwner = this

        binding.dateDlg.init(cYear,cMonth,cDay,object : DatePicker.OnDateChangedListener{
            override fun onDateChanged(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                viewModel.dateSet(year,monthOfYear+1,dayOfMonth)
            }
        })




        return view
    }
}