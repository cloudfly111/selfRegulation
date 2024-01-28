package com.judy.self.regulation.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.judy.self.regulation.R
import com.judy.self.regulation.MainViewModel
import com.judy.self.regulation.adapter.MainScreenAdapter
import com.judy.self.regulation.dataClass.RankData
import com.judy.self.regulation.databinding.DialogStatusBinding
import com.judy.self.regulation.databinding.FragmentLoginBinding
import com.judy.self.regulation.databinding.FragmentMainBinding
import com.judy.self.regulation.dialog.StatusDialog
import com.judy.self.regulation.model.LocalModel
import kotlinx.coroutines.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding

    private lateinit var viewModel: MainViewModel

    val adapter = MainScreenAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //set fragment view-binding
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        var isShowDialog : Boolean = false

        binding.rankList.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = this@MainFragment.adapter
        }

        viewModel.isOpenStatusLiveData.observe(this.viewLifecycleOwner, Observer {
            isShowDialog = it
        })
        binding.progressBarFrameLayout.apply {
            this.setOnClickListener {
                showDialog(isShowDialog)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            //以異步方式取得本地資料後, 更新UI
            val dataResult = async {  getData()}
            val result = dataResult.await()
            updateListView(result)
        }
        var count = 2
        binding.button.setOnClickListener {
            count++
            viewLifecycleOwner.lifecycleScope.launch {
                val list = async {
                    val list : MutableList<RankData> = mutableListOf()
                    for (i in 1..count)  list.add(RankData(rank = "01","工作"))
                    list
                }
                Log.i("MainFragment","list=${list.await()}")
                withContext(Dispatchers.Main){updateListView(list.await())}
            }
        }
    }

    fun showDialog(isShow: Boolean) {
        val message: String = getString(R.string.defaultStatusMessage)
        val dialog = StatusDialog(this.requireContext(),message)
        if(isShow) dialog.show()
    }

    //取得 local端資料
    suspend fun getData() = withContext(Dispatchers.IO){
        return@withContext LocalModel.setMainListData(activity = requireActivity())
    }

    //更新首頁 List Data
    suspend fun updateListView(list:MutableList<RankData>)= withContext(Dispatchers.Main){
        adapter.setListData(list)
    }




}