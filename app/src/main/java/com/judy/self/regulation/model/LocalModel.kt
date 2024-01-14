package com.judy.self.regulation.model

/**  local 端資料存取*/
class LocalModel {

    companion object {
        fun setMainListData(
            rankArray: Array<String>,
            categoryArray: Array<String>
        ): MutableList<MutableMap<String, String>> {

            val RANK_KEY: String = "RANK"
            val TITLE_Key: String = "TITLE"

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