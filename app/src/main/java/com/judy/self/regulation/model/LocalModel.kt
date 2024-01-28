package com.judy.self.regulation.model

import android.app.Activity
import com.judy.self.regulation.R
import com.judy.self.regulation.dataClass.RankData

/**  local 端資料存取*/
class LocalModel {

    companion object {
        fun setMainListData(
            activity:Activity,
        ): MutableList<RankData> {

            val  rankArray = activity.resources.getStringArray(R.array.rankArray)
            val categoryArray = activity.resources.getStringArray(R.array.categoryArray)
            val list = MutableList<RankData>(3) { index: Int ->
                val mapData = RankData(rank = rankArray.get(index).toString(), title = categoryArray.get(index).toString())
                mapData
            }
            return list
        }
    }
}