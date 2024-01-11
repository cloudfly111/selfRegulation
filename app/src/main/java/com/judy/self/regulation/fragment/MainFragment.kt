package com.judy.self.regulation.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.judy.self.regulation.R
import com.judy.self.regulation.MainViewModel
import com.judy.self.regulation.adapter.MainScreenAdapter
import com.judy.self.regulation.databinding.DialogStatusBinding
import com.judy.self.regulation.databinding.FragmentLoginBinding
import com.judy.self.regulation.databinding.FragmentMainBinding
import com.judy.self.regulation.dialog.StatusDialog

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

        val list = setListData()
        val adapter = MainScreenAdapter(list)
        adapter.notifyDataSetChanged()
        binding.rankList.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }

    fun setListData(): MutableList<MutableMap<String, String>> {
        val RANK_KEY: String = "RANK"
        val TITLE_Key: String = "TITLE"
        val rankArray = resources.getStringArray(R.array.rankArray)
        val categoryArray = resources.getStringArray(R.array.categoryArray)
        val list = MutableList<MutableMap<String, String>>(3) { index: Int ->
            val mapData = mutableMapOf<String, String>()
            mapData.put(RANK_KEY, rankArray.get(index).toString())
            mapData.put(TITLE_Key, categoryArray.get(index).toString())
            mapData
        }
        return list
    }

    fun showDialog(isShow: Boolean) {
        val message: String = getString(R.string.defaultStatusMessage)
        val dialog = StatusDialog(this.requireContext(),message)
        if(isShow) dialog.show()
    }

}