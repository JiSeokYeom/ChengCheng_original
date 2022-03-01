package com.one_day.one_drink_a_day.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.one_day.one_drink_a_day.activity.MainActivity
import com.one_day.one_drink_a_day.adapter.MainRvAdapter
import com.one_day.one_drink_a_day.R
import com.one_day.one_drink_a_day.databinding.FragmentMainPersonalBinding
import com.one_day.one_drink_a_day.model.MainRecyclerViewItem
import com.one_day.one_drink_a_day.style.MyItemDecoration
import com.one_day.one_drink_a_day.viewmodel.MainViewModel


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




