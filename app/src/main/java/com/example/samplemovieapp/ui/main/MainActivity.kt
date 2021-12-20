package com.example.samplemovieapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.samplemovieapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.navView)
        val navController = findNavController(R.id.navHostFragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> navView.visibility = View.VISIBLE
                R.id.navigation_tv_shows -> navView.visibility = View.VISIBLE
                R.id.navigation_movies -> navView.visibility = View.VISIBLE
                else -> navView.visibility = View.GONE
            }
        }
        val appBarConfiguration = AppBarConfiguration(
                setOf(R.id.navigation_home, R.id.navigation_tv_shows, R.id.navigation_movies)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}