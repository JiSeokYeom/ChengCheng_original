package com.one_day.one_drink_a_day.adapter

import android.app.Activity
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one_day.one_drink_a_day.CropLibrary
import com.one_day.one_drink_a_day.model.MainRecyclerViewItem
import com.one_day.one_drink_a_day.databinding.RvitemBinding

class MainRvAdapter : RecyclerView.Adapter<MainRvAdapter.ViewHolder>(){
    var mData = mutableListOf<MainRecyclerViewItem>()
    private val cropLibrary = CropLibrary(Activity())
    private val TAG = "MainRvAdapter"

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }
    //객체 저장 변수
    private lateinit var mOnItemClickListener: OnItemClickListener
    //객체 전달 메서드
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }
    inner class ViewHolder(var binding: RvitemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: MainRecyclerViewItem){
            binding.rvTitleImg.setImageBitmap(cropLibrary.stringToBitmap(item.img))
            Log.d(TAG,"${cropLibrary.stringToBitmap(item.img)}")
            Log.d(TAG, item.img)

            binding.rvItemTitle.text = item.title
            binding.view.setOnClickListener {
                val itemPos = adapterPosition
                mOnItemClickListener.onItemClick(binding.view,itemPos)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setData(data : List<MainRecyclerViewItem>){
        mData.clear()
        mData.addAll(data)
    }
}