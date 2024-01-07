package com.judy.self.regulation.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.judy.self.regulation.R
import com.judy.self.regulation.MainViewModel
import com.judy.self.regulation.adapter.MainScreenAdapter
import com.judy.self.regulation.databinding.FragmentLoginBinding
import com.judy.self.regulation.databinding.FragmentMainBinding

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
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val mapData = mutableMapOf<String,String>()
        mapData.put("RANK","01")
        mapData.put("TITLE","健康")
        val list = mutableListOf(mapData,mapData,mapData)
        val adapter = MainScreenAdapter(list)
        adapter.notifyDataSetChanged()
        binding.rankList.layoutManager = LinearLayoutManager(context)
        binding.rankList.adapter=adapter

    }

}