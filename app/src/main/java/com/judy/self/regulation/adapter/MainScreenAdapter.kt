package com.judy.self.regulation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.judy.self.regulation.databinding.ItemStatusBinding

class MainScreenAdapter(val mainItemList: MutableList<MutableMap<String,String>>): RecyclerView.Adapter<MainScreenAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(val binding:ItemStatusBinding) : RecyclerView.ViewHolder(binding.root){

        fun setContent(item:MutableMap<String,String>){
            binding.rankTextView.text = item["RANK"]
            binding.titleTextview.text = item["TITLE"]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = mainItemList[position]
        holder.setContent(data)
    }

    override fun getItemCount(): Int {
        return mainItemList.size
    }
}