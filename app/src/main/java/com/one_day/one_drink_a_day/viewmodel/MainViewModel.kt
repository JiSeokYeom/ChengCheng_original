package com.one_day.one_drink_a_day.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.one_day.one_drink_a_day.model.MainRecyclerViewItem

class MainViewModel : ViewModel(){
    var rvLiveData : MutableLiveData<ArrayList<MainRecyclerViewItem>> = MutableLiveData()
   // val dataLiveData = MutableLiveData<List<MainRecyclerViewItem>>()
    private val datas = arrayListOf<MainRecyclerViewItem>()

    fun getListAll(){

    }
    fun dataAdd(mainRecyclerViewItem: MainRecyclerViewItem){
        datas.add(mainRecyclerViewItem)
     //   dataLiveData.value = datas
    }

    fun dataDelete(mainRecyclerViewItem: MainRecyclerViewItem){
        datas.remove(mainRecyclerViewItem)
    }

}