package com.judy.self.regulation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.judy.self.regulation.model.LocalModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
   // 是否開啟狀態視窗
    val isOpenStatusLiveData = MutableLiveData<Boolean>(true)
    /** 儲存首頁list資料*/
    val mainListData = MutableLiveData<MutableList<MutableMap<String, String>>>()
}