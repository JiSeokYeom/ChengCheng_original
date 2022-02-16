package com.example.chengchengoriginal.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chengchengoriginal.R
import com.example.chengchengoriginal.activity.MainActivity
import com.example.chengchengoriginal.adapter.MainRvAdapter
import com.example.chengchengoriginal.databinding.FragmentMainPersonalBinding
import com.example.chengchengoriginal.model.MainRecyclerViewItem
import com.example.chengchengoriginal.style.MyItemDecoration
import com.example.chengchengoriginal.viewmodel.MainViewModel


class MainFragment_Personal : Fragment() {
    var mainActivity : MainActivity? = null
    private var mbinding: FragmentMainPersonalBinding? = null
    private val binding get() = mbinding!!
    private lateinit var adapterRV: MainRvAdapter
    private val datas = arrayListOf<MainRecyclerViewItem>()
    private val TAG = "MainFragment_Personal"
    private lateinit var mainViewModel : MainViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        mbinding = FragmentMainPersonalBinding.inflate(inflater,container,false)

        mainViewModel = MainViewModel()
        adapterRV = MainRvAdapter()

        binding.mainPersonalRv.setHasFixedSize(true)
        binding.mainPersonalRv.adapter = adapterRV

      /*  mainViewModel.dataLiveData.observe(viewLifecycleOwner, {
            it.let { item->
                datas.add(item)
            }
        })*/
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 1111111"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 2222222"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 3333333"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 4444444"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 5555555"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 6666666"))
        datas.add(MainRecyclerViewItem(R.drawable.testimg,"테스트 7777777"))
        adapterRV.mData = datas
        binding.mainPersonalRv.layoutManager = GridLayoutManager(context,3,RecyclerView.VERTICAL,false)
        binding.mainPersonalRv.addItemDecoration(MyItemDecoration())
        return binding.root

    }




    override fun onDestroy() {
        mbinding = null
        super.onDestroy()
    }

}




