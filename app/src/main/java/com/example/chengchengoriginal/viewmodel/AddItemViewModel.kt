package com.example.chengchengoriginal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddItemViewModel : ViewModel() {
    var numCount : MutableLiveData<Int> = MutableLiveData()
    init {
        numCount.value = 0
    }

    fun letterInputCount(letterCount : Int){
        numCount.value = letterCount
    }
}