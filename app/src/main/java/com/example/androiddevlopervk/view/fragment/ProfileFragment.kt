package com.example.androiddevlopervk.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.androiddevlopervk.R

class ProfileFragment: Fragment(R.layout.profile_fragment){

    lateinit var image: ImageView
    lateinit var nameAndSername: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image = view.findViewById(R.id.image_profile)
        nameAndSername = view.findViewById(R.id.name_amd_sername)

        image.setImageResource(R.drawable.favorites2)
        nameAndSername.text = getString(R.string.name)
    }
}