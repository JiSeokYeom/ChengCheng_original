package com.example.chengchengoriginal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chengchengoriginal.model.MainRecyclerViewItem
import com.example.chengchengoriginal.databinding.RvitemBinding

class MainRvAdapter : RecyclerView.Adapter<MainRvAdapter.ViewHolder>(){
    var mData = arrayListOf<MainRecyclerViewItem>()

    inner class ViewHolder(var binding: RvitemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: MainRecyclerViewItem){
            binding.titleImg.setImageResource(item.img)
            binding.title.text = item.title
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRvAdapter.ViewHolder {
        val binding = RvitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainRvAdapter.ViewHolder, position: Int) {
        val item = mData[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

}