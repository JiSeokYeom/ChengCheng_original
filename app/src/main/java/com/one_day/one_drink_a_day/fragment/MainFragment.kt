package com.one_day.one_drink_a_day.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.one_day.one_drink_a_day.activity.MainActivity
import com.one_day.one_drink_a_day.adapter.MainRvAdapter
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.FragmentMainBinding
import com.one_day.one_drink_a_day.firebase.FirebaseDB
import com.one_day.one_drink_a_day.model.MainRecyclerViewItem
import com.one_day.one_drink_a_day.style.MyItemDecoration
import com.one_day.one_drink_a_day.viewmodel.MainViewModel


class MainFragment : Fragment() {
    var mainActivity : MainActivity? = null
    private lateinit var adapterRV: MainRvAdapter
    private val datas = arrayListOf<MainRecyclerViewItem>()
    private val TAG = "MainFragment"
    private lateinit var mainViewModel : MainViewModel
    private var titleName : ArrayList<String> = arrayListOf()
    private var titleImg : String? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        val binding = FragmentMainBinding.inflate(inflater,container,false)

        mainViewModel = MainViewModel()
        adapterRV = MainRvAdapter()

        binding.mainPersonalRv.setHasFixedSize(true)
        binding.mainPersonalRv.adapter = adapterRV

        itemAdd()

        adapterRV.mData = datas
        binding.mainPersonalRv.layoutManager = GridLayoutManager(context,3,RecyclerView.VERTICAL,false)
        binding.mainPersonalRv.addItemDecoration(MyItemDecoration())
        return binding.root

    }

    private fun itemAdd(){
        val myRef = FirebaseDB.database.child("publicList").child("ItemList")
        var count  = 0
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot : DataSnapshot in snapshot.children){
                    Log.d(TAG,"for문 내부 ${dataSnapshot.child("TitleName").value}")
                    titleName = dataSnapshot.child("TitleName").value as ArrayList<String>

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
       /* datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 1111111"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 2222222"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 3333333"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 4444444"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 5555555"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 6666666"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 7777777"))*/
    }

}




