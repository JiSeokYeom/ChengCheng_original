package com.one_day.one_drink_a_day.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.one_day.one_drink_a_day.model.MainRecyclerViewItem

class MainViewModel : ViewModel(){
    val dataLiveData = MutableLiveData<List<MainRecyclerViewItem>>()
    private val datas = arrayListOf<MainRecyclerViewItem>()

    init {
        dataLiveData.value = datas
    }
    /*fun getListAll():{

    }*/
    fun dataAdd(mainRecyclerViewItem: MainRecyclerViewItem){
        datas.add(mainRecyclerViewItem)
        dataLiveData.value = datas
    }

    fun dataDelete(mainRecyclerViewItem: MainRecyclerViewItem){
        datas.remove(mainRecyclerViewItem)
    }

}