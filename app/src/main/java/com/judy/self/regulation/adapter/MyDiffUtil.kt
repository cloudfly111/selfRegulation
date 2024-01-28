package com.judy.self.regulation.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.judy.self.regulation.dataClass.RankData

/** 資料變更類型 */
enum class ContentType {
    /** 標題*/
    Title,
    /** 排名*/
    Rank
}

class MyDiffUtil:DiffUtil.ItemCallback<RankData>(){
    val tag: String = "MyDiffUtil"

    override fun areItemsTheSame(oldItem: RankData, newItem: RankData): Boolean {
        Log.v(tag,"[areItemsTheSame] oldItem=$oldItem, newItem=$newItem")
        //當 oldItem.rank == newItem.rank 回傳 false => 代表資料不同會直接更新資料
        //當 oldItem.rank == newItem.rank 回傳 true => 代表相同 rank但內容不一定一致，所以會再跑到 areContentsTheSame 確認剩下內容是否相同
       return oldItem.rank == newItem.rank
    }

    override fun areContentsTheSame(oldItem: RankData, newItem: RankData): Boolean {
        Log.v(tag,"[areContentsTheSame] oldItem=$oldItem, newItem=$newItem")
        //當 oldItem == newItem 回傳 false => 代表資料內容不同，會跑到 getChangePayload
        //當 oldItem == newItem 回傳 true => 代表資料內容相同，則不更新資料
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: RankData, newItem: RankData): Any? {
        Log.v(tag,"[getChangePayload] oldItem=$oldItem, newItem=$newItem")
        //當 areItemsTheSame = true and areContentsTheSame = false 會進來
        //透過回傳資料變更類型 判斷 Item 更新內容
        //當Item內容只有 title 會被更新，回傳資料變更類型為 ContentType.Title 否則回傳 null
        if(!oldItem.title.equals(newItem.title)){
            return ContentType.Title
        }else{
           return null
        }
    }

}

