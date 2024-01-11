package com.judy.self.regulation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
   // 是否開啟狀態視窗
    val isOpenStatusLiveData = MutableLiveData<Boolean>(true)

}