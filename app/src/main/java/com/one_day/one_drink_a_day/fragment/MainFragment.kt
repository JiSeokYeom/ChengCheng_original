package com.one_day.one_drink_a_day.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.one_day.one_drink_a_day.CropLibrary
import com.one_day.one_drink_a_day.SharedObject
import com.one_day.one_drink_a_day.activity.MainActivity
import com.one_day.one_drink_a_day.adapter.MainRvAdapter
import com.one_day.one_drink_a_day.databinding.FragmentMainBinding
import com.one_day.one_drink_a_day.dialog.ProgressDialog
import com.one_day.one_drink_a_day.model.MainRecyclerViewItem
import com.one_day.one_drink_a_day.style.MyItemDecoration
import com.one_day.one_drink_a_day.viewmodel.MainViewModel


class MainFragment : Fragment() {
    var mainActivity : MainActivity? = null
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }
    private lateinit var adapterRV: MainRvAdapter
    private lateinit var datas : MutableList<MainRecyclerViewItem>
    private val TAG = "MainFragment"
    private val progressDialog = ProgressDialog()
    private lateinit var binding : FragmentMainBinding
    private lateinit var cropLibrary : CropLibrary

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        binding = FragmentMainBinding.inflate(inflater,container,false)

        adapterRV = MainRvAdapter()

        datas = mutableListOf()
        progressDialog.show(childFragmentManager,"progressDialog")


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


        cropLibrary = CropLibrary(requireActivity(),this)

        SharedObject.imgBitmapArray.clear()

        mainViewModel.getListAll().observe(viewLifecycleOwner) {
            adapterRV.setData(it)
       //     mainViewModel.item.clear()
            adapterRV.notifyDataSetChanged()
        }



        // adapterRV.mData = datas


        return binding.root

    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG,"리즘 됨!!!!!!!!!!!!!")
      //  mainViewModel.getListAllTest()
    }

}




