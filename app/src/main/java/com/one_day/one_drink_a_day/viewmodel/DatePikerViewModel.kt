package com.one_day.one_drink_a_day.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class DatePikerViewModel :ViewModel() {
    private val calendar = Calendar.getInstance()
    private val TAG = "DatePikerViewModel"
    private lateinit var date : String
    var viewModelYear : MutableLiveData<Int> = MutableLiveData()
    var viewModelMonth : MutableLiveData<Int> = MutableLiveData()
    var viewModelDay : MutableLiveData<Int> = MutableLiveData()


    init {
        viewModelYear.value = calendar.get(Calendar.YEAR)
        viewModelMonth.value = calendar.get(Calendar.MONTH)+1
        viewModelDay.value = calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun dateSet(sYear : Int, sMonth : Int, sDay : Int){
        viewModelYear.value = sYear
        viewModelMonth.value = sMonth
        viewModelDay.value = sDay
    }









}