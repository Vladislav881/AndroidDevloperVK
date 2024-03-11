package com.example.androiddevlopervk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController


        val catalogButton = findViewById<ImageButton>(R.id.catalog)
        val basketButton = findViewById<ImageButton>(R.id.basket)
        val favoritesButton = findViewById<ImageButton>(R.id.favorites)
        val profileButton = findViewById<ImageButton>(R.id.profile)
        val title = findViewById<TextView>(R.id.title)

        catalogButton.setOnClickListener {
            title.text = getString(R.string.catalog)
            Navigation.findNavController(this, R.id.navHostFragment)
                .navigate(R.id.action_to_store_catalog)
        }

        basketButton.setOnClickListener {
            title.text = getString(R.string.basket)
            Navigation.findNavController(this, R.id.navHostFragment)
                .navigate(R.id.action_to_basket)
        }

        favoritesButton.setOnClickListener {
            title.text = getString(R.string.favorites)
            Navigation.findNavController(this, R.id.navHostFragment)
                .navigate(R.id.action_to_favorites)
        }

        profileButton.setOnClickListener {
            title.text = getString(R.string.profile)
            Navigation.findNavController(this, R.id.navHostFragment)
                .navigate(R.id.action_to_profil)
        }

    }
}