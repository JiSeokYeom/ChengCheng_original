package com.one_day.one_drink_a_day.adapter

import android.app.Activity
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one_day.one_drink_a_day.CropLibrary
import com.one_day.one_drink_a_day.model.MainRecyclerViewItem
import com.one_day.one_drink_a_day.databinding.RvitemBinding

class MainRvAdapter : RecyclerView.Adapter<MainRvAdapter.ViewHolder>(){
    var mData = arrayListOf<MainRecyclerViewItem>()
    val cropLibrary = CropLibrary(Activity())

    inner class ViewHolder(var binding: RvitemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: MainRecyclerViewItem){
            binding.rvTitleImg.setImageBitmap(cropLibrary.stringToBitmap(item.img))
         //   binding.rvTitleImg.setImageBitmap(item.img as Bitmap)
            Log.d("어댑터","${cropLibrary.stringToBitmap(item.img)}")
            Log.d("어댑터2", item.img)

            binding.rvItemTitle.text = item.title
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

}