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
import androidx.recyclerview.widget.LinearLayoutManager
import com.judy.self.regulation.R
import com.judy.self.regulation.MainViewModel
import com.judy.self.regulation.adapter.MainScreenAdapter
import com.judy.self.regulation.databinding.DialogStatusBinding
import com.judy.self.regulation.databinding.FragmentLoginBinding
import com.judy.self.regulation.databinding.FragmentMainBinding
import com.judy.self.regulation.dialog.StatusDialog
import com.judy.self.regulation.model.LocalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding

    private lateinit var viewModel: MainViewModel

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

        viewModel.isOpenStatusLiveData.observe(this.viewLifecycleOwner, Observer {
            isShowDialog = it
        })
        binding.progressBarFrameLayout.apply {
            this.setOnClickListener {
                showDialog(isShowDialog)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val result = getData()
            updateListView(result)
        }
    }

    fun showDialog(isShow: Boolean) {
        val message: String = getString(R.string.defaultStatusMessage)
        val dialog = StatusDialog(this.requireContext(),message)
        if(isShow) dialog.show()
    }

    //取得 local端資料
    suspend fun getData() = withContext(Dispatchers.IO){
        delay(1000)
        return@withContext LocalModel.setMainListData(activity = requireActivity())
    }

    //更新首頁 List Data
    suspend fun updateListView(list:MutableList<MutableMap<String, String>>)= withContext(Dispatchers.Main){
        val adapter = MainScreenAdapter(list)
        binding.rankList.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
        adapter.notifyDataSetChanged()
    }

}