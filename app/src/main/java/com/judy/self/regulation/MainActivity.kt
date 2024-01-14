package com.judy.self.regulation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.judy.self.regulation.databinding.ActivityMainBinding
import com.judy.self.regulation.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    private var isInitial: Boolean = false
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //set View binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //由 view binding 連接 navigationView
        val navHostFragment= supportFragmentManager.findFragmentById(R.id.container)
        val navController =navHostFragment?.findNavController()
        if (navController != null) {
            binding.navigationView.setupWithNavController(navController)
        }
        //初次進入顯示歡迎頁
        if(!isInitial)startActivity(Intent(this,WelcomeActivity::class.java))
        isInitial = true
    }

}