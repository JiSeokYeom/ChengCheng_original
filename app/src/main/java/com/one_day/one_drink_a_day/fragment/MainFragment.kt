package com.one_day.one_drink_a_day.fragment

import android.content.Context
import android.graphics.Bitmap
import android.icu.text.CaseMap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.one_day.one_drink_a_day.CropLibrary
import com.one_day.one_drink_a_day.activity.MainActivity
import com.one_day.one_drink_a_day.adapter.MainRvAdapter
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.SharedObject
import com.one_day.one_drink_a_day.databinding.FragmentMainBinding
import com.one_day.one_drink_a_day.firebase.FirebaseDB
import com.one_day.one_drink_a_day.model.MainRecyclerViewItem
import com.one_day.one_drink_a_day.style.MyItemDecoration
import com.one_day.one_drink_a_day.viewmodel.DatePikerViewModel
import com.one_day.one_drink_a_day.viewmodel.MainViewModel


class MainFragment : Fragment() {
    var mainActivity : MainActivity? = null
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }
    private lateinit var adapterRV: MainRvAdapter
    private lateinit var datas : MutableList<MainRecyclerViewItem>
    private val TAG = "MainFragment"
    private val myRef = FirebaseDB.database.child("publicList").child("ItemList")
    private lateinit var binding : FragmentMainBinding
    private var titleName : ArrayList<String> = arrayListOf()
    private var titleImg : ArrayList<String> = arrayListOf()

    private lateinit var cropLibrary : CropLibrary
  //  private var testImg : Bitmap? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        binding = FragmentMainBinding.inflate(inflater,container,false)

        adapterRV = MainRvAdapter()

        datas = mutableListOf()
        binding.lifecycleOwner = this
        binding.refresh.setOnRefreshListener {

            binding.refresh.isRefreshing = false
        }
        binding.apply {
            mainRv.setHasFixedSize(true)
            mainRv.adapter = adapterRV
            mainRv.layoutManager = GridLayoutManager(context,3,RecyclerView.VERTICAL,false)
            mainRv.addItemDecoration(MyItemDecoration())
        }

      /*  initData()
        adapterRV.mData = datas*/


        cropLibrary = CropLibrary(requireActivity(),this)

        SharedObject.imgBitmapArray.clear()

        mainViewModel.getListAll().observe(viewLifecycleOwner) {
            adapterRV.setData(it)
            mainViewModel.item.clear()
            adapterRV.notifyDataSetChanged()
        }

       // adapterRV.mData = datas


        return binding.root

    }

    private fun initData(){
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot : DataSnapshot in snapshot.children) {
                    datas.add(MainRecyclerViewItem(dataSnapshot.child("TitleImg").value.toString(),dataSnapshot.child("TitleName").value.toString()))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
/*    private fun itemAdd(){
        val myRef = FirebaseDB.database.child("publicList").child("ItemList")
        var count  = 0

        titleName = arrayListOf()
        titleImg = arrayListOf()

        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                datas.clear()
                for (dataSnapshot : DataSnapshot in snapshot.children){
                    Log.d(TAG,"for문 내부 ${dataSnapshot.child("TitleName").value}")
                    titleName.add(dataSnapshot.child("TitleName").value.toString())
                    titleImg.add(dataSnapshot.child("TitleImg").value.toString())
                    datas.add(MainRecyclerViewItem(dataSnapshot.child("TitleImg").value.toString(),(dataSnapshot.child("TitleName").value.toString())))
                    Log.d(TAG,"for문 name 내부 $titleName")
                    Log.d(TAG,"for문 Img 내부 $titleImg")
                }
             //   testImg = snapshot.child("TitleImg").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
       *//* datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 1111111"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 2222222"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 3333333"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 4444444"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 5555555"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 6666666"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 7777777"))*//*
    }*/

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"리즘 됨!!!!!!!!!!!!!")
        mainViewModel.getListAllTest()
    }

}




