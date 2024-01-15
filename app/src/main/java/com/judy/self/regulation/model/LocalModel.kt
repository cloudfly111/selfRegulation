package com.judy.self.regulation.model

import android.app.Activity
import com.judy.self.regulation.R

/**  local 端資料存取*/
class LocalModel {

    companion object {
        fun setMainListData(
            activity:Activity,
        ): MutableList<MutableMap<String, String>> {

            val RANK_KEY: String = "RANK"
            val TITLE_Key: String = "TITLE"
            val  rankArray = activity.resources.getStringArray(R.array.rankArray)
            val categoryArray = activity.resources.getStringArray(R.array.categoryArray)
            val list = MutableList<MutableMap<String, String>>(3) { index: Int ->
                val mapData = mutableMapOf<String, String>()
                mapData.put(RANK_KEY, rankArray.get(index).toString())
                mapData.put(TITLE_Key, categoryArray.get(index).toString())
                mapData
            }
            return list
        }
    }
}