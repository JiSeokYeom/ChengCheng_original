package com.one_day.one_drink_a_day.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.one_day.one_drink_a_day.model.MainRecyclerViewItem
import com.one_day.one_drink_a_day.databinding.RvitemBinding

class MainRvAdapter : RecyclerView.Adapter<MainRvAdapter.ViewHolder>(){
    var mData = arrayListOf<MainRecyclerViewItem>()

    inner class ViewHolder(var binding: RvitemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: MainRecyclerViewItem){
            binding.rvTitleImg.setImageResource(item.img)
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