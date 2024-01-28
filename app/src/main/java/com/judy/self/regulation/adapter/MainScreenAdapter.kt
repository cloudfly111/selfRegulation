package com.judy.self.regulation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.judy.self.regulation.dataClass.RankData
import com.judy.self.regulation.databinding.ItemStatusBinding

class MainScreenAdapter(): ListAdapter<RankData, MainScreenAdapter.ItemViewHolder>(MyDiffUtil()) {
    private var adapterList = mutableListOf<RankData>()

    inner class ItemViewHolder(val binding:ItemStatusBinding) : RecyclerView.ViewHolder(binding.root){

        fun setContent(item:RankData){
            binding.rankTextView.text = item.rank
            binding.titleTextview.text = item.title
        }
        fun setTitle(title:String){
            binding.titleTextview.text = title
        }
    }

    fun setListData(list: MutableList<RankData>) {
        adapterList = currentList
        Log.v("MainScreenAdapter", "adapterList = $adapterList")
        adapterList = list
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = getItem(position)
        holder.setContent(data)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        Log.v("MainScreenAdapter", "payloads = $payloads")
        if (payloads.isNotEmpty()) {
            for (load in payloads) {
                //payloads 接收到 ContentType.Title 代表 Item 只有標題被修改時，所以只更新標題內容
                if (load == ContentType.Title) {
                    Log.v("MainScreenAdapter", "onBindViewHolder = setTitleContent")
                    holder.setTitle(getItem(position).title)
                }
            }
        } else {
            Log.v("MainScreenAdapter", "onBindViewHolder = setAllContent")
            super.onBindViewHolder(holder, position, payloads)
        }


    }



}