package com.judy.self.regulation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.judy.self.regulation.R
import com.judy.self.regulation.databinding.FragmentStressBinding


class StressFragment : Fragment() {

    private lateinit var binding: FragmentStressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //set fragment view-binding
        binding = FragmentStressBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    companion object {

    }
}