package com.one_day.one_drink_a_day.dialog

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.one_day.one_drink_a_day.databinding.DatepikerBinding
import com.one_day.one_drink_a_day.fragment.AddItemFragment4
import com.one_day.one_drink_a_day.viewmodel.DatePikerViewModel
import java.util.*


class DatePikerDialog : DialogFragment() {
    private val viewModel: DatePikerViewModel by lazy {
        ViewModelProvider(this).get(DatePikerViewModel::class.java)
    }
    private lateinit var binding: DatepikerBinding
    private val TAG = "DatePikerDialog"
    private lateinit var addItemFragment4: AddItemFragment4

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //addItem = context as AddItem
      //  addItemTest2 = context as AddItemTest2
    }
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
        addItemFragment4 = AddItemFragment4()

        binding.dateDlg.init(cYear,cMonth,cDay,object : DatePicker.OnDateChangedListener{
            override fun onDateChanged(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                viewModel.dateSet(year,monthOfYear+1,dayOfMonth)
            }
        })

        binding.saveBtn.setOnClickListener {
            addItemFragment4.datePickerResult(viewModel.viewModelYear.value!!,viewModel.viewModelMonth.value!!,viewModel.viewModelDay.value!!)
            dialog?.dismiss()
        }

        return view
    }
}