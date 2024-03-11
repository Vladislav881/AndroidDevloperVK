package com.example.androiddevlopervk.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevlopervk.R
import com.example.androiddevlopervk.view.recycler_view.CatalogAdapter
import com.example.androiddevlopervk.view_model.MainViewModel

class ProductDescriptionFragment: Fragment(R.layout.product_description_fragment) {

    private val mainViewModel: MainViewModel by activityViewModels()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById<TextView>(R.id.description_title_product)
        val rating = view.findViewById<TextView>(R.id.description_rating)
        val prise = view.findViewById<TextView>(R.id.description_price_product)
        val description = view.findViewById<TextView>(R.id.description_product)

        val recyclerView: RecyclerView = view.findViewById(R.id.description_image_product_recycler_vew)

        val position = arguments?.getInt("position", -1) ?: -1

        if (position != -1) {
            title.text = mainViewModel.productsLiveData.value!![position].title
            rating.text = getString(R.string.reting) + mainViewModel.productsLiveData.value!![position].rating.toString()
            prise.text = getString(R.string.prise) + mainViewModel.productsLiveData.value!![position].price.toString()
            description.text = mainViewModel.productsLiveData.value!![position].description


        }

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        mainViewModel.imageList.observe(viewLifecycleOwner) { images ->
            images?.let {
                val adapter = CatalogAdapter(mainViewModel, images[position])
                recyclerView.adapter = adapter
            }
        }

    }
}