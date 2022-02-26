package com.example.chengchengoriginal.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class DatePikerViewModel :ViewModel() {
    var year : MutableLiveData<Int> = MutableLiveData()
    var month : MutableLiveData<Int> = MutableLiveData()
    var day : MutableLiveData<Int> = MutableLiveData()
    private val calendar = Calendar.getInstance()
    private val TAG = "DatePikerViewModel"

    init {
        year.value = calendar.get(Calendar.YEAR)
        month.value = calendar.get(Calendar.MONTH)+1
        day.value = calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun dateSet(sYear : Int, sMonth : Int, sDay : Int){
        year.value = sYear
        month.value = sMonth
        day.value = sDay
    }
}